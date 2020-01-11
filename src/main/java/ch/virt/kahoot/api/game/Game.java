package ch.virt.kahoot.api.game;

import ch.virt.kahoot.api.data.controller.ControllerResponse;
import ch.virt.kahoot.api.data.player.PlayerResponse;
import ch.virt.kahoot.api.data.status.StatusResponse;
import com.google.gson.Gson;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.client.BayeuxClient;

/**
 * @author VirtCode
 * @version 1.0
 */
public abstract class Game {

    protected BayeuxClient client;

    protected String host, gameID, clientID;
    protected int messageCount;

    public void setClient(BayeuxClient client, String host, String gameid, String clientID){
        Gson gson = new Gson();

        this.host = host;
        this.gameID = gameid;
        this.clientID = clientID;

        client.getChannel("/service/player").subscribe((channel, message) -> {
            System.out.println(message.getJSON());
            playerMessageReceived(gson.fromJson(message.getJSON(), PlayerResponse.class));
        });
        client.getChannel("/service/controller").subscribe((channel, message) -> {
            controllerMessageReceived(gson.fromJson(message.getJSON(), ControllerResponse.class));
            System.out.println(message.getJSON());
        });
        client.getChannel("/service/status").subscribe((channel, message) -> {
            statusMessageReceived(gson.fromJson(message.getJSON(), StatusResponse.class));
            System.out.println(message.getJSON());
        });

        this.client = client;
        connectionSucceeded();
    }

    public abstract void playerMessageReceived(PlayerResponse response);
    public abstract void controllerMessageReceived(ControllerResponse message);
    public abstract void statusMessageReceived(StatusResponse message);

    public abstract void connectionFailed(String error);
    public abstract void connectionSucceeded();

    public void send(String channel, Object content){
        client.getChannel(channel).publish(content, (channel1, message) -> {
            if(channel1.getId().equals("/service/controller")) {
                controllerMessageReceived(new Gson().fromJson(message.getJSON(), ControllerResponse.class));
            }else if(channel1.getId().equals("/service/player")) {
                playerMessageReceived(new Gson().fromJson(message.getJSON(), PlayerResponse.class));
            }else if(channel1.getId().equals("/service/status")) {
                statusMessageReceived(new Gson().fromJson(message.getJSON(), StatusResponse.class));
            }
        });
    }
}
