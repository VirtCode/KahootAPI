package ch.virt.kahoot.api.data.player;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * This class represents the data of a Player response
 * @author VirtCode
 * @version 1.0
 */
public class PlayerResponseData {
    @SerializedName("gameid")
    private String gameID;
    @SerializedName("host")
    private String host;
    @SerializedName("id")
    private int ID;
    @SerializedName("type")
    private String type;
    @SerializedName("cid")
    private String cid;
    @SerializedName("content")
    private String content;
    private PlayerResponseContent parsedContent;

    /**
     * Returns the parsed content of a request
     * @return parsed content of a request
     */
    public PlayerResponseContent getContent() {
        if (parsedContent == null) parsedContent = new Gson().fromJson(content, PlayerResponseContent.class);
        return parsedContent;
    }

    /**
     * Returns the id of the game which is running
     * @return id of the game which is running
     */
    public String getGameID() {
        return gameID;
    }

    /**
     * Returns the the host the game is running on
     * @return the host the game is running on
     */
    public String getHost() {
        return host;
    }

    /**
     * Returns the id of the response
     * @return id of the response
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the type of the response
     * @return type of the response
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the cid of the response
     * @return cid of the response
     */
    public String getCid() {
        return cid;
    }


}
