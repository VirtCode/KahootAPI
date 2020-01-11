package ch.virt.kahoot.api.data.player.objects;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a json object of a kahoot Nemesis
 * @author VirtCode
 * @version 1.0
 */
public class Nemesis {
    @SerializedName("name")
    private String name;
    @SerializedName("isGhost")
    private boolean ghost;
    @SerializedName("cid")
    private String cid;
    @SerializedName("isKicked")
    private boolean kicked;
    @SerializedName("totalScore")
    private int score;

    /**
     * Getter
     * @return name of the nemesis
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the nemesis is a ghost
     * @return is nemesis ghost
     */
    public boolean isGhost() {
        return ghost;
    }

    /**
     * Returns the cid of your Nemesis
     * @return cid of your Nemesis
     */
    public String getCid() {
        return cid;
    }

    /**
     * Returns whether the nemesis is kicked
     * @return is kicked
     */
    public boolean isKicked() {
        return kicked;
    }

    /**
     * Returns the total score of the nemesis
     * @return total score of the nemesis
     */
    public int getScore() {
        return score;
    }
}
