package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.appointment;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.AppointmentServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
public class AppointmentTestCase
{
    @Test
    public void getAppointmentList()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UserTO userTO=new UserTO();
        userTO.setId(11);
        userTO.setPhone("1122334455");
        userTO.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InRlc3QyQGdtYWlsLmNvbSIsImVtYWlsIjoiQWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3ZlcnNpb24iOiJWMy4xIiwibmJmIjoxNTg2NjQ5NjY1LCJleHAiOjE1ODY4MjI0NjUsImlhdCI6MTU4NjY0OTY2NX0.IM_hRtRmPRXat_vQasUKhvCkzImc6W9vz369oi3CQTI");
        userTO.setEmail("test2@gmail.com");
        userTO.setPasswd("test");


        AppointmentServerApi appointmentServerApi=new AppointmentServerApi();
        appointmentServerApi.getAllAppointments(userTO.getToken(), new Callback<List<AppointmentTO>>() {
            @Override
            public void onResponse(Call<List<AppointmentTO>> call, Response<List<AppointmentTO>> response) {
                Assert.assertEquals(200,response.code());
            }

            @Override
            public void onFailure(Call<List<AppointmentTO>> call, Throwable t) {

            }
        });
    }

    @Test
    public void requestAppointment()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UserTO userTO=new UserTO();
        userTO.setId(11);
        userTO.setPhone("1122334455");
        userTO.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InRlc3QyQGdtYWlsLmNvbSIsImVtYWlsIjoiQWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3ZlcnNpb24iOiJWMy4xIiwibmJmIjoxNTg2NjQ5NjY1LCJleHAiOjE1ODY4MjI0NjUsImlhdCI6MTU4NjY0OTY2NX0.IM_hRtRmPRXat_vQasUKhvCkzImc6W9vz369oi3CQTI");
        userTO.setEmail("test2@gmail.com");
        userTO.setPasswd("test");


        AppointmentServerApi appointmentServerApi=new AppointmentServerApi();
        appointmentServerApi.addAppointment(userTO.getToken(), 3, new Date().getTime(), new Callback<AppointmentTO>() {
            @Override
            public void onResponse(Call<AppointmentTO> call, Response<AppointmentTO> response) {
                Assert.assertEquals(200,response.code());
            }

            @Override
            public void onFailure(Call<AppointmentTO> call, Throwable t) {

            }
        });
    }


    @Test
    public void updateAppointment()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UserTO userTO=new UserTO();
        userTO.setId(11);
        userTO.setPhone("1122334455");
        userTO.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InRlc3QyQGdtYWlsLmNvbSIsImVtYWlsIjoiQWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3ZlcnNpb24iOiJWMy4xIiwibmJmIjoxNTg2NjQ5NjY1LCJleHAiOjE1ODY4MjI0NjUsImlhdCI6MTU4NjY0OTY2NX0.IM_hRtRmPRXat_vQasUKhvCkzImc6W9vz369oi3CQTI");
        userTO.setEmail("test2@gmail.com");
        userTO.setPasswd("test");


        AppointmentServerApi appointmentServerApi=new AppointmentServerApi();
        appointmentServerApi.updateAppointmentStatus(userTO.getToken(), 1, AppointmentTO.EAppointmentStatus.Accepted.getValue(), new Callback<AppointmentTO>() {
            @Override
            public void onResponse(Call<AppointmentTO> call, Response<AppointmentTO> response) {
                Assert.assertEquals(200,response.code());
            }

            @Override
            public void onFailure(Call<AppointmentTO> call, Throwable t) {

            }
        });
    }

}
