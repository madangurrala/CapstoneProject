package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.bl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.PropertyServiceAPI;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyServiceBL
{
    private PropertyServiceAPI propertyServiceAPI;
    public PropertyServiceBL() {
        propertyServiceAPI=new PropertyServiceAPI();
    }

    public void getProperties(String token) {
        propertyServiceAPI.getProperties(token, new Callback<List<PropertyTO>>() {
            @Override
            public void onResponse(Call<List<PropertyTO>> call, Response<List<PropertyTO>> response) {

            }

            @Override
            public void onFailure(Call<List<PropertyTO>> call, Throwable t) {

            }
        });
    }

    public void getUserProperties(String token) {
        propertyServiceAPI.getUserProperties(token, new Callback<List<PropertyTO>>() {
            @Override
            public void onResponse(Call<List<PropertyTO>> call, Response<List<PropertyTO>> response) {

            }

            @Override
            public void onFailure(Call<List<PropertyTO>> call, Throwable t) {

            }
        });
    }
}
