package ch.virt.kahoot.api.connection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This class connects over its host to kahoot
 * @author Rob-- and VirtCode
 * (In fact rob-- wrote the most of it)
 * @version 1.0
 */
public class KahootAPI {

    private static final String BASE_URL = "https://kahoot.it/";

    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    private KahootInterface api = retrofit.create(KahootInterface.class);

    /**
     * This is a simple kahoot interface
     */
    private interface KahootInterface {
        @GET("reserve/session/{gameCode}/")
        Call<KahootResponse> reserveSession(@Path("gameCode") String gameCode);
    }

    /**
     * This method reserves the session for a gamecode
     * @param gameCode kahoot gamecode
     * @param callback callback if finished
     */
    public void reserveSession(String gameCode, final KahootCallback callback) {
        Call<KahootResponse> request = api.reserveSession(gameCode);

        request.enqueue(new Callback<KahootResponse>() {
            @Override
            public void onResponse(Call<KahootResponse> call, Response<KahootResponse> response) {
                callback.onResponse(response);
            }

            @Override
            public void onFailure(Call<KahootResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

}
