package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl;

import com.google.gson.JsonObject;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IUserServiceAPI {
    //@FormUrlEncoded
    //@Headers( "Content-Type: application/json" )
    //@Headers( { "Accept: text/plain"} )
    @POST("login")
    Call<UserTO> login(@Body RequestBody requestBody);

    @POST(value = "user")
    Call<UserTO> register(@Body RequestBody requestBody);
}
