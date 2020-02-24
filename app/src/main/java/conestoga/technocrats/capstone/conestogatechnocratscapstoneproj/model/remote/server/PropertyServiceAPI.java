package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl.IPropertyServiceAPI;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.root.ServerService;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PropertyServiceAPI {
    private IPropertyServiceAPI iPropertyServiceAPI;
    private final String baseUrl = "";

    public PropertyServiceAPI() {
        Retrofit retrofit = new ServerService(baseUrl).getRetrofit();
        iPropertyServiceAPI = retrofit.create(IPropertyServiceAPI.class);
    }

    public void getProperties(String token, Callback<List<PropertyTO>> callback) {
        if (callback == null) {
            return;
        }
        Call<List<PropertyTO>> call = iPropertyServiceAPI.getProperties(token);
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
