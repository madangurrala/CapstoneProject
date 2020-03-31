package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl.IAppointmentServiceAPI;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class AppointmentServerApi
{
    private IAppointmentServiceAPI iAppointmentServiceAPI;
    private String baseUrl="";

    public AppointmentServerApi()
    {
        baseUrl=CoreServerApi.BASE_URL;
    }

    public void getAllAppointments(String token, Callback<List<AppointmentTO>> callback)
    {
        if(callback==null)
        {
            return;
        }
        Retrofit retrofit= new CoreServerApi(baseUrl,token).getRetrofit();
        iAppointmentServiceAPI=retrofit.create(IAppointmentServiceAPI.class);
        Call<List<AppointmentTO>> call=iAppointmentServiceAPI.getAllAppointments();
        call.enqueue(callback);
    }

}
