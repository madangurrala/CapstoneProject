package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPropertyServiceAPI {
    //@Headers("Content-Type: application/json")
    @GET(value = "property")
    Call<List<PropertyTO>> getProperties();

    @POST(value = "property")
    Call<PropertyTO> registerProperty(@Body RequestBody requestBody);

    @PUT(value = "property/{id}")
    Call<PropertyTO> updateProperty(@Path("id") int id, @Body RequestBody requestBody);
}
