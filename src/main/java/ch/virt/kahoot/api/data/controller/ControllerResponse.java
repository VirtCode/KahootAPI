package ch.virt.kahoot.api.data.controller;

import ch.virt.kahoot.api.data.status.objects.ExtTime;
import com.google.gson.annotations.SerializedName;

/**
 * This class represents a response form the /service/controller channel
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

    /**
     * Returns the channel the response has been sent on
     * (normally /service/controller)
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
     * Returns the data of the response
     * @return data of the response
     */
    public ControllerResponseData getData() {
        return data;
    }

    /**
     * Returns whether the last request was successful
     * @return last request was successful
     */
    public boolean isSuccessful() {
        return successful;
    }
}
