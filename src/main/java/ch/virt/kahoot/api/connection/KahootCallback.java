package ch.virt.kahoot.api.connection;

import retrofit2.Response;

/**
 * This class represents a callback for the kahoot api
 * @author VirtCode
 * @version 1.0
 */
public interface KahootCallback {

    /**
     * Is called when the kahoot servers respond
     * @param response response from the servers
     */
    void onResponse(Response<KahootResponse> response);

    /**
     * Is called when the request failed
     * @param t throwable of the failure
     */
    void onFailure(Throwable t);
}