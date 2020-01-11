package ch.virt.kahoot.api.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author VirtCode
 * @version 1.0
 */
public class ExtTime {
    @SerializedName("timetrack")
    private String timeTrack;

    public String getTimeTrack() {
        return timeTrack;
    }
}
