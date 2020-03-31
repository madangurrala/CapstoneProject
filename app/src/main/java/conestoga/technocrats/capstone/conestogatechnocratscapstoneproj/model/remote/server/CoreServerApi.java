package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoreServerApi {
    public static final String BASE_URL = "https://rentspace.azurewebsites.net/";
    private Retrofit retrofit;
    private String token;

    public CoreServerApi(String baseUrl, String token) {
        this.token = token;
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(new ConnectionReqInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


    private class ConnectionReqInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (originalRequest.url().toString().contains("/property") ||
                    originalRequest.url().toString().contains("/appointment")) {
                Request newRequest = originalRequest.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Authorization", String.format("Bearer %s", token))
                        .build();

                return chain.proceed(newRequest);
            }
            return chain.proceed(originalRequest);
        }
    }
}
