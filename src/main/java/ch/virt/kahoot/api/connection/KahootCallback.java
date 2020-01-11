package ch.virt.kahoot.api.connection;

import retrofit2.Response;

public interface KahootCallback {
    void onResponse(Response<KahootResponse> response);
    void onFailure(Throwable t);
}