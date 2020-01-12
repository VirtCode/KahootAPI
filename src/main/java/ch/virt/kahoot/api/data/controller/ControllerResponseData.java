package ch.virt.kahoot.api.data.controller;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the data for a controller response
 * @author VirtCode
 * @version 1.0
 */
public class ControllerResponseData {

    @SerializedName("type")
    private String type;
    @SerializedName("cid")
    private String cid;
    @SerializedName("error")
    private String errorCode;
    @SerializedName("description")
    private String description;

    /**
     * Returns the type of the data
     * @return type of the data
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the cid of the response
     * @return cid of the response
     */
    public String getCid() {
        return cid;
    }

    /**
     * Returns the error code if an error occurred
     * @return error code if an error occurred
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Returns the description of the event occurred
     * @return description of the event occurred
     */
    public String getDescription() {
        return description;
    }
}
