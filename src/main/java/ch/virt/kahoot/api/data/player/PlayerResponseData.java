package ch.virt.kahoot.api.data.player;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
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

    public PlayerResponseContent getContent() {
        if (parsedContent == null) parsedContent = new Gson().fromJson(content, PlayerResponseContent.class);
        return parsedContent;
    }

    public String getGameID() {
        return gameID;
    }

    public String getHost() {
        return host;
    }

    public int getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public String getCid() {
        return cid;
    }


}
