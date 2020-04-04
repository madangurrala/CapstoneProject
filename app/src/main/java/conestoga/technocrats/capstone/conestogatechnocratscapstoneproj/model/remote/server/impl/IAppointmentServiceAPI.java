package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IAppointmentServiceAPI
{
    @GET(value = "appointment")
    public Call<List<AppointmentTO>> getAllAppointments();

    @POST(value = "appointment")
    public Call<AppointmentTO> registerAppointment(@Body RequestBody requestBody);
}
