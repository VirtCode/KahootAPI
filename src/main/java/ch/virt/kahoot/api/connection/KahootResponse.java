package ch.virt.kahoot.api.connection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class represents a json object for the response
 * @author Rob-- and VirtCode
 * (In fact rob-- wrote the most of it)
 * @version 1.0
 */
public class KahootResponse {

    @SerializedName("twoFactorAuth")
    @Expose
    private Boolean twoFactorAuth;
    @SerializedName("challenge")
    @Expose
    private String challenge;

    /**
     * Returns whether the game is secured with 2factor auth
     * @return game is secured with 2factor auth
     */
    public Boolean getTwoFactorAuth() {
        return twoFactorAuth;
    }

    /**
     * Returns the challenge that needs to be solved
     * @return challenge that needs to be solved
     */
    public String getChallenge() {
        return challenge;
    }
}