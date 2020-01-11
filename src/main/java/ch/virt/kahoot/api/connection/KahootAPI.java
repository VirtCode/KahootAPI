package ch.virt.kahoot.api.connection;

import com.google.gson.annotations.SerializedName;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class KahootAPI {

    private static final String BASE_URL = "https://kahoot.it/"; //https://kahoot.it/reserve/session/234667/?1499372391528";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private KahootInterface api = retrofit.create(KahootInterface.class);

    private interface KahootInterface {
        @GET("reserve/session/{gameCode}/")
        Call<KahootResponse> reserveSession(@Path("gameCode") String gameCode);
    }

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
