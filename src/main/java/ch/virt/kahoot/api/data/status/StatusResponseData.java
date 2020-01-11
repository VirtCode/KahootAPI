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

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }
}
