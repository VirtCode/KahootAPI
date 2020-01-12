package ch.virt.kahoot.api.game;

import ch.virt.kahoot.api.data.controller.ControllerResponse;
import ch.virt.kahoot.api.data.player.PlayerResponse;
import ch.virt.kahoot.api.data.status.StatusResponse;
import com.google.gson.Gson;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.client.BayeuxClient;

/**
 * This class represents the most simple wrapper for a kahoot game
 * @author VirtCode
 * @version 1.0
 */
public abstract class Game {

    protected BayeuxClient client;

    protected String host, gameID;

    protected boolean doJsonLogs = false;

    /**
     * Sets the cometd client so the game is ready to go
     * @param client client
     * @param host host where the game is hosted
     * @param gameid id of the game being in
     */
    public void setClient(BayeuxClient client, String host, String gameid){
        Gson gson = new Gson();

        this.host = host;
        this.gameID = gameid;

        client.getChannel("/service/player").subscribe((channel, message) -> {
            if(doJsonLogs) System.out.println("Message from /service/player: " + message.getJSON());
            playerMessageReceived(gson.fromJson(message.getJSON(), PlayerResponse.class));
        });
        client.getChannel("/service/controller").subscribe((channel, message) -> {
            if(doJsonLogs) System.out.println("Message from /service/controller: " + message.getJSON());
            controllerMessageReceived(gson.fromJson(message.getJSON(), ControllerResponse.class));
        });
        client.getChannel("/service/status").subscribe((channel, message) -> {
            if(doJsonLogs) System.out.println("Message from /service/status: " + message.getJSON());
            statusMessageReceived(gson.fromJson(message.getJSON(), StatusResponse.class));
        });

        this.client = client;
        connectionSucceeded();
    }

    /**
     * Event when a message is received from /player/message
     * @param response message
     */
    public abstract void playerMessageReceived(PlayerResponse response);
    /**
     * Event when a message is received from /player/controller
     * @param message message
     */
    public abstract void controllerMessageReceived(ControllerResponse message);
    /**
     * Event when a message is received from /player/status
     * @param message message
     */
    public abstract void statusMessageReceived(StatusResponse message);

    /**
     * Event when the connection to the servers failed
     * @param error error message
     */
    public abstract void connectionFailed(String error);

    /**
     * Event when the connection Succeeded
     */
    public abstract void connectionSucceeded();

    /**
     * Sends an object to the given channel
     * @param channel channel to send to
     * @param content object to send
     */
    public void send(String channel, Object content){
        client.getChannel(channel).publish(content, (channel1, message) -> {
            switch (channel1.getId()) {
                case "/service/controller":
                    if(doJsonLogs) System.out.println("Response from /service/controller: " + message.getJSON());
                    controllerMessageReceived(new Gson().fromJson(message.getJSON(), ControllerResponse.class));
                    break;
                case "/service/player":
                    if(doJsonLogs) System.out.println("Response from /service/player: " + message.getJSON());
                    playerMessageReceived(new Gson().fromJson(message.getJSON(), PlayerResponse.class));
                    break;
                case "/service/status":
                    if(doJsonLogs) System.out.println("Response from /service/status: " + message.getJSON());
                    statusMessageReceived(new Gson().fromJson(message.getJSON(), StatusResponse.class));
                    break;
            }
        });
    }
}
