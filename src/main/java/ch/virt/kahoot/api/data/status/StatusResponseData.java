package ch.virt.kahoot.api.data.status;

import com.google.gson.annotations.SerializedName;

/**
 * @author VirtCode
 * @version 1.0
 */
public class StatusResponseData {

    @SerializedName("type")
    private String type;
    @SerializedName("status")
    private String status;

    /**
     * Returns the type of data
     * @return type of data
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the status the response has sent
     * @return status the response has sent
     */
    public String getStatus() {
        return status;
    }
}
