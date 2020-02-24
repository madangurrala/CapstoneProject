package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface IUserServiceAPI {
    //@FormUrlEncoded
    @POST(value = "/conestoga/api/login")
    Call<UserTO> login(@Field(value = "email") String email, @Field(value = "passwd") String passwd);

    @POST(value = "/conestoga/api/logout")
    Call<Void> logout(@Field(value = "token") String token);
}
