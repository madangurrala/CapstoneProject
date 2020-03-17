package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.bl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.PropertyServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyServerBL
{
    private PropertyServerApi propertyServerApi;
    public PropertyServerBL() {
        propertyServerApi =new PropertyServerApi();
    }

    public void getProperties(String token) {
        propertyServerApi.getProperties(token, new Callback<List<PropertyTO>>() {
            @Override
            public void onResponse(Call<List<PropertyTO>> call, Response<List<PropertyTO>> response) {

            }

            @Override
            public void onFailure(Call<List<PropertyTO>> call, Throwable t) {

            }
        });
    }

    public void getUserProperties(String token) {
        propertyServerApi.getUserProperties(token, new Callback<List<PropertyTO>>() {
            @Override
            public void onResponse(Call<List<PropertyTO>> call, Response<List<PropertyTO>> response) {

            }

            @Override
            public void onFailure(Call<List<PropertyTO>> call, Throwable t) {

            }
        });
    }
}
