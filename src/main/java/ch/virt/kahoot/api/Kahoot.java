package ch.virt.kahoot.api;

import ch.virt.kahoot.api.connection.GameExistsListener;
import ch.virt.kahoot.api.connection.KahootAPI;
import ch.virt.kahoot.api.connection.KahootCallback;
import ch.virt.kahoot.api.connection.KahootResponse;
import ch.virt.kahoot.api.game.Game;
import io.webfolder.ducktape4j.Duktape;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.LongPollingTransport;

import java.util.Base64;
import java.util.HashMap;

import retrofit2.Response;

/**
 * This class connects and sets up the cometd connection to the kahoot servers
 * @author Rob-- and VirtCode
 * (In fact rob-- wrote the most of it)
 * @version 1.0
 */
public class Kahoot {

    /**
     * Used to join a kahoot game without creating an instance
     * @param username name of the player
     * @param gameID id of game to join
     * @param game wrapper for process the events
     */
    public static void joinGame(String username, String gameID, Game game){
        new Kahoot(username, gameID, game).join();
    }

    /**
     * Following interface and method is to run and decode the challenge,
     * we define `console.log` to return the offset and log it via the logger.
     */
    interface console {
        void log(Object... message);
    }

    /**
     * creates the console but since there are mostly no logs, they aren't printed to console
     */
    static private console c = message -> {
    };

    private String username;
    private String gameCode;
    private KahootAPI api;
    private Game game;

    /**
     * Creates a Kahoot instance with the credentials
     * @param username name of the player
     * @param gameCode code of game to join
     * @param game wrapper to process the events of the game
     */
    public Kahoot(String username, String gameCode, Game game) {
        this.username = username;
        this.gameCode = gameCode;
        this.game = game;
        this.api = new KahootAPI();
    }

    /**
     * Join the specified kahootgame
     */
    public void join() {
        reserveSession();
    }

    /**
     * Checks whether a game exists
     * @param gameCode gamecode of game to check
     * @param listener listener for the response
     */
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

    /**
     * Reserves the session for the game
     */
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

                assert response.body() != null;
                String decoded = Kahoot.runChallenge(response.body().getChallenge());
                String token = response.headers().get("x-kahoot-session-token");

                assert token != null;
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

    /**
     * Sets the comet connection to the servers up
     * @param cometToken token for the cometd connection
     */
    private void setupComet(String cometToken) {
        String url = "https://kahoot.it/cometd/" + this.gameCode + "/" + cometToken;

        final BayeuxClient client = new BayeuxClient(url, LongPollingTransport.create(null));

        client.handshake((channel, message) -> {
            if (message.isSuccessful()) {
                client.getChannel("/service/controller").publish(getLoginData());

                game.setClient(client, "kahoot.it", this.gameCode);
            } else {
                game.connectionFailed("Unsuccessful handshake, unable to join game.");
            }
        });
    }

    /**
     * Returns the object for the login data
     * @return login data
     */
    private HashMap<String, Object> getLoginData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type", "login");
        data.put("gameid", this.gameCode);
        data.put("host", "kahoot.it");
        data.put("name", this.username);

        return data;
    }

    /**
     * Runs the challenge given to the client
     * @param challenge challenge
     * @return decoded challenge
     */
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

        return answer;
    }

    /**
     * decodes the token from the decoded challenge
     * @param sessionToken session token
     * @param decodedChallenge decoded challenge
     * @return decoded token
     */
    private static String getToken(String sessionToken, String decodedChallenge) {
        byte[] decodedToken = Base64.getDecoder().decode(sessionToken);
        byte[] challenge = decodedChallenge.getBytes();

        for (int i = 0; i < decodedToken.length; i++) {
            decodedToken[i] ^= challenge[i % challenge.length];
        }

        return new String(decodedToken);
    }

}
