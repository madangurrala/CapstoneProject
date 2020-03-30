package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl.IPropertyServiceAPI;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PropertyServerApi {
    private IPropertyServiceAPI iPropertyServiceAPI;
    private String baseUrl;

    public PropertyServerApi() {
        baseUrl=CoreServerApi.BASE_URL;
    }

    public void getProperties(String token, Callback<List<PropertyTO>> callback) {
        if (callback == null) {
            return;
        }
        Retrofit retrofit = new CoreServerApi(baseUrl,token).getRetrofit();
        iPropertyServiceAPI = retrofit.create(IPropertyServiceAPI.class);
        /*Request.Builder builder=new Request.Builder();
        builder.addHeader("Authorization","Bearer"+token);
        builder.addHeader("Content-Type","application/json");*/
        Call<List<PropertyTO>> call = iPropertyServiceAPI.getProperties();
        call.enqueue(callback);
    }

    public void getUserProperties(String token, Callback<List<PropertyTO>> callback) {
        if (callback == null) {
            return;
        }
        Call<List<PropertyTO>> call = iPropertyServiceAPI.getUserProperties(token);
        call.enqueue(callback);
    }
}
