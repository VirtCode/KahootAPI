package ch.virt.kahoot.api;

import ch.virt.kahoot.api.connection.GameExistsListener;
import ch.virt.kahoot.api.connection.KahootAPI;
import ch.virt.kahoot.api.connection.KahootCallback;
import ch.virt.kahoot.api.connection.KahootResponse;
import ch.virt.kahoot.api.data.HandshakeEssentials;
import ch.virt.kahoot.api.game.Game;
import com.google.gson.Gson;
import io.webfolder.ducktape4j.Duktape;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.LongPollingTransport;

import java.util.Base64;
import java.util.HashMap;

import retrofit2.Response;

public class Kahoot {

    public static void joinGame(String username, String gameID, Game game){
        new Kahoot(username, gameID, game).join();
    }

    // Following interface and method is to run and decode the challenge,
    // we define `console.log` to return the offset and log it via the logger.
    interface console {
        void log(Object... message);
    }

    static private console c = message -> {
        StringBuilder sb = new StringBuilder();
        for (Object o : message) {
            sb.append(o);
        }
        System.out.println(sb.toString());
    };

    private String username;
    private String gameCode;
    private KahootAPI api;
    private Game game;

    public Kahoot(String username, String gameCode, Game game) {
        this.username = username;
        this.gameCode = gameCode;
        this.game = game;
        this.api = new KahootAPI();
    }

    public void join() {
        reserveSession();
    }

    public static void gameExists(final String gameCode, final GameExistsListener listener) {
        KahootAPI api = new KahootAPI();
        api.reserveSession(gameCode, new KahootCallback() {
            @Override
            public void onResponse(Response<KahootResponse> response) {
                listener.gameExists(response.code() != 404);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void reserveSession() {
        api.reserveSession(this.gameCode, new KahootCallback() {
            @Override
            public void onResponse(Response<KahootResponse> response) {
                if (response.code() == 404) {
                    game.connectionFailed("Game with code #" + gameCode + " no longer exists, unable to join.");
                    return;
                }

                if (response.code() != 200) {
                    game.connectionFailed("An error occured trying to join game with code #" + gameCode);
                    return;
                }

                String decoded = Kahoot.runChallenge(response.body().getChallenge());
                String token = response.headers().get("x-kahoot-session-token");

                if (token.isEmpty()) {
                    game.connectionFailed("Unable to reserve session, no session token received.");
                    return;
                }

                String cometToken = Kahoot.getToken(token, decoded);
                setupComet(cometToken);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setupComet(String cometToken) {

        String url = "https://kahoot.it/cometd/" + this.gameCode + "/" + cometToken;

        final BayeuxClient client = new BayeuxClient(url, LongPollingTransport.create(null));

        client.handshake((channel, message) -> {
            String clientID = new Gson().fromJson(message.getJSON(), HandshakeEssentials.class).getClientId();
            if (message.isSuccessful()) {
                System.out.println("Successful handshake, attempting to join game...");
                client.getChannel("/service/controller").publish(getLoginData());

                game.setClient(client, "kahoot.it", this.gameCode, clientID);
            } else {
                game.connectionFailed("Unsuccessful handshake, unable to join game.");
            }
        });
    }

    private HashMap<String, Object> getLoginData() {
        HashMap<String, Object> data = new HashMap();
        data.put("type", "login");
        data.put("gameid", this.gameCode);
        data.put("host", "kahoot.it");
        data.put("name", this.username);

        return data;
    }

    private static String runChallenge(String challenge) {
        Duktape duktape = Duktape.create();

        String angularFunctions = ""
                + "angular = {\n"
                    + "isArray: function(){ return true; },\n"
                    + "isDate: function(){ return true; },\n"
                    + "isObject: function(){ return true; },\n"
                    + "isString: function(){ return true; },\n"
                + "};\n";

        String lodashFunction = ""
            + "_ = {\n"
                + "replace: function () {\n"
                    + "var string = \"\" + (arguments.length <= 0 ? undefined : arguments[0]);\n"
                    + "return arguments.length < 3 ? string : string.replace(arguments.length <= 1 ? undefined : arguments[1], arguments.length <= 2 ? undefined : arguments[2]);\n"
                + "}\n"
            + "};\n";

        String consoleFunction = ""
            + "console = {\n"
                 + "log: function() {} \n"
            + "};";

        duktape.set("console", console.class, c);

        String answer = duktape.evaluate(angularFunctions + lodashFunction + consoleFunction + challenge).toString();
        duktape.close();

        System.err.println("Challenge decoded: " + answer);
        return answer;
    }

    private static String getToken(String sessionToken, String decodedChallenge) {
        byte[] decodedToken = Base64.getDecoder().decode(sessionToken);
        byte[] challenge = decodedChallenge.getBytes();

        for (int i = 0; i < decodedToken.length; i++) {
            decodedToken[i] ^= challenge[i % challenge.length];
        }

        String token = new String(decodedToken);
        System.err.println("Decoded token: " + token);
        return token;
    }

}
