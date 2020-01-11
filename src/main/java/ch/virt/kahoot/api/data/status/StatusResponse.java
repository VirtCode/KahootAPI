package ch.virt.kahoot.api.data.status;

import ch.virt.kahoot.api.data.ExtTime;
import com.google.gson.annotations.SerializedName;

/**
 * @author VirtCode
 * @version 1.0
 */
public class StatusResponse {
    @SerializedName("channel")
    private String channel;
    @SerializedName("ext")
    private ExtTime extTime;
    @SerializedName("data")
    private StatusResponseData data;

    public String getChannel() {
        return channel;
    }

    public ExtTime getExtTime() {
        return extTime;
    }

    public StatusResponseData getData() {
        return data;
    }
}
