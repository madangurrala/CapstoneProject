package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server;

import com.google.gson.JsonObject;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl.IAppointmentServiceAPI;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import okhttp3.MediaType;
import okhttp3.RequestBody;
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

    public void addAppointment(String token,long propertyId,long appointmentDate, Callback<AppointmentTO> callback)
    {
        if(callback==null)
        {
            return;
        }
        Retrofit retrofit= new CoreServerApi(baseUrl,token).getRetrofit();
        iAppointmentServiceAPI=retrofit.create(IAppointmentServiceAPI.class);

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("PropertyId",(int)propertyId);
        jsonObject.addProperty("AppointmentDate",appointmentDate);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());

        Call<AppointmentTO> call=iAppointmentServiceAPI.registerAppointment(requestBody);
        call.enqueue(callback);
    }

    public void updateAppointmentStatus(String token,int appointmentId,String status, Callback<AppointmentTO> callback)
    {
        if(callback==null)
        {
            return;
        }
        Retrofit retrofit= new CoreServerApi(baseUrl,token).getRetrofit();
        iAppointmentServiceAPI=retrofit.create(IAppointmentServiceAPI.class);

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Status",status);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());

        Call<AppointmentTO> call=iAppointmentServiceAPI.updateAppointmentStatus(appointmentId,requestBody);
        call.enqueue(callback);
    }

}
