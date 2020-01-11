package ch.virt.kahoot.api.data.controller;

import com.google.gson.annotations.SerializedName;

/**
 * @author VirtCode
 * @version 1.0
 */
public class ControllerResponseData {
    @SerializedName("type")
    private String type;
    @SerializedName("cid")
    private String cid;

    public String getType() {
        return type;
    }

    public String getCid() {
        return cid;
    }
}
