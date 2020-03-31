package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.impl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IAppointmentServiceAPI
{
    @GET(value = "appointment")
    public Call<List<AppointmentTO>> getAllAppointments();
}
