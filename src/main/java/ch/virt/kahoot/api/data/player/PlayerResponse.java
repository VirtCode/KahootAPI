package ch.virt.kahoot.api.data.player;

import ch.virt.kahoot.api.data.ExtTime;
import com.google.gson.annotations.SerializedName;

/**
 * @author VirtCode
 * @version 1.0
 */
public class PlayerResponse {

    public static final int QUESTION_PREPARE = 1;
    public static final int QUESTION_START = 2;
    public static final int QUIZ_END = 3;
    public static final int QUESTION_END = 4;
    public static final int ANSWER_POST = 7;
    public static final int ANSWER_CORRECT = 8;
    public static final int QUIZ_START = 9;
    public static final int QUIZ_FEEDBACK = 12;
    public static final int QUIZ_CLOSED = 13;
    public static final int CONNECTION_CONFIRMED = 14;

    @SerializedName("channel")
    private String channel;
    @SerializedName("ext")
    private ExtTime extTime;
    @SerializedName("data")
    private PlayerResponseData data;

    public String getChannel() {
        return channel;
    }

    public ExtTime getExtTime() {
        return extTime;
    }

    public PlayerResponseData getData() {
        return data;
    }
}
