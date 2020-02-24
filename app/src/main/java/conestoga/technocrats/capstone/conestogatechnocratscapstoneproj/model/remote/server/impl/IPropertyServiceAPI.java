package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPropertyServiceAPI {
    @GET(value = "/conestoga/api/all_properties")
    Call<List<PropertyTO>> getProperties(@Query(value = "token") String token);

    @GET(value = "/conestoga/api/all_user_properties")
    Call<List<PropertyTO>> getUserProperties(@Query(value = "token") String token);
}
