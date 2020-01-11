package ch.virt.kahoot.api.data.status.objects;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the kahoot ext json object
 * @author VirtCode
 * @version 1.0
 */
public class ExtTime {

    @SerializedName("timetrack")
    private String timeTrack;

    /**
     * Returns the time track of the ext
     * @return time track of the ext
     */
    public String getTimeTrack() {
        return timeTrack;
    }
}
