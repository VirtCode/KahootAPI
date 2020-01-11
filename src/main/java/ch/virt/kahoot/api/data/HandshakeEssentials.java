package ch.virt.kahoot.api.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author VirtCode
 * @version 1.0
 */
public class HandshakeEssentials {
    @SerializedName("clientId")
    private String clientid;

    public String getClientId() {
        return clientid;
    }
}
