package ch.virt.kahoot.api.data.status;

import ch.virt.kahoot.api.data.status.objects.ExtTime;
import com.google.gson.annotations.SerializedName;

/**
 * This class represents a response in the status channel
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

    /**
     * Returns the channel the response has been sent on
     * (normally /service/status)
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
     * Returns the data of the status response
     * @return data of the status response
     */
    public StatusResponseData getData() {
        return data;
    }
}
