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
}
