package ch.virt.kahoot.api.data.controller;

import ch.virt.kahoot.api.data.ExtTime;
import com.google.gson.annotations.SerializedName;

/**
 * @author VirtCode
 * @version 1.0
 */
public class ControllerResponse {
    @SerializedName("channel")
    private String channel;
    @SerializedName("ext")
    private ExtTime extTime;
    @SerializedName("data")
    private ControllerResponseData data;
    @SerializedName("successful")
    private boolean successful;

    public String getChannel() {
        return channel;
    }

    public ExtTime getExtTime() {
        return extTime;
    }

    public ControllerResponseData getData() {
        return data;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
