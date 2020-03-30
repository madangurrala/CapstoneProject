package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IPropertyServiceAPI {
    //@Headers("Content-Type: application/json")
    @GET(value = "property")
    Call<List<PropertyTO>> getProperties();

    @GET(value = "/conestoga/api/all_user_properties")
    Call<List<PropertyTO>> getUserProperties(@Query(value = "token") String token);
}
