package ch.virt.kahoot.api.connection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KahootResponse {

    @SerializedName("twoFactorAuth")
    @Expose
    private Boolean twoFactorAuth;
    @SerializedName("challenge")
    @Expose
    private String challenge;

    public Boolean getTwoFactorAuth() {
        return twoFactorAuth;
    }

    public void setTwoFactorAuth(Boolean twoFactorAuth) {
        this.twoFactorAuth = twoFactorAuth;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

}