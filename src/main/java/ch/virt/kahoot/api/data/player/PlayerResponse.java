package ch.virt.kahoot.api.data.player;

import ch.virt.kahoot.api.data.status.objects.ExtTime;
import com.google.gson.annotations.SerializedName;

/**
 * This class represents a kahoot json component of a player response
 * @author VirtCode
 * @version 1.0
 */
public class PlayerResponse {

    /**
     * Magic constants for all processed server responses
     */
    public static final int QUESTION_PREPARE = 1;
    public static final int QUESTION_START = 2;
    public static final int QUIZ_END = 3;
    public static final int QUESTION_END = 4;
    public static final int ANSWER_CORRECT = 8;
    public static final int QUIZ_START = 9;
    public static final int QUIZ_FINISHED = 10;
    public static final int QUIZ_FEEDBACK = 12;
    public static final int QUIZ_CLOSED = 13;
    public static final int CONNECTION_CONFIRMED = 14;

    @SerializedName("channel")
    private String channel;
    @SerializedName("ext")
    private ExtTime extTime;
    @SerializedName("data")
    private PlayerResponseData data;

    /**
     * Returns the channel the response has been sent on
     * (Normally /service/player)
     * @return channel the response has been sent on
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Returns the ext time of the response
     * @return ext time of the response
     */
    public ExtTime getExtTime() {
        return extTime;
    }

    /**
     * Returns the data of the player response
     * @return data of the player response
     */
    public PlayerResponseData getData() {
        return data;
    }
}
