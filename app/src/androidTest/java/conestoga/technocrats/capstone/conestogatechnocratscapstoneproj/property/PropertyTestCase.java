package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.property;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.PropertyServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.MainPropertyPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IPropertiesContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RunWith(AndroidJUnit4.class)
public class PropertyTestCase
{
    @Test
    public void getPropertyList()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MainPropertyPresenter mainPropertyPresenter=new MainPropertyPresenter(appContext, new IPropertiesContract() {
            @Override
            public void fillPropertiesRecycleView(List<PropertyTO> propertyTOS)
            {
                Assert.assertNotNull(propertyTOS);
                Assert.assertTrue(propertyTOS.size()>0);
            }

            @Override
            public void addProperty(boolean status) {

            }
        });
        mainPropertyPresenter.getPropertiesList();
    }

    @Test
    public void postProperty()
    {
        PropertyTO propertyTO=new PropertyTO();
        propertyTO.setUserId(11);
        propertyTO.setTitle("Test Property");


        UserTO userTO=new UserTO();
        userTO.setId(11);
        userTO.setPhone("1122334455");
        userTO.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InRlc3QyQGdtYWlsLmNvbSIsImVtYWlsIjoiQWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3ZlcnNpb24iOiJWMy4xIiwibmJmIjoxNTg2NjQ5NjY1LCJleHAiOjE1ODY4MjI0NjUsImlhdCI6MTU4NjY0OTY2NX0.IM_hRtRmPRXat_vQasUKhvCkzImc6W9vz369oi3CQTI");
        userTO.setEmail("test2@gmail.com");
        userTO.setPasswd("test");


        PropertyServerApi propertyServerApi=new PropertyServerApi();
        propertyServerApi.registerProperty(userTO.getToken(), propertyTO, new Callback<PropertyTO>() {
            @Override
            public void onResponse(Call<PropertyTO> call, Response<PropertyTO> response) {
                Assert.assertEquals(200,response.code());
            }

            @Override
            public void onFailure(Call<PropertyTO> call, Throwable t) {

            }
        });
    }

    @Test
    public void updateProperty()
    {
        PropertyTO propertyTO=new PropertyTO();
        propertyTO.setUserId(11);
        propertyTO.setTitle("Test Property");


        UserTO userTO=new UserTO();
        userTO.setId(11);
        userTO.setPhone("1122334455");
        userTO.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InRlc3QyQGdtYWlsLmNvbSIsImVtYWlsIjoiQWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3ZlcnNpb24iOiJWMy4xIiwibmJmIjoxNTg2NjQ5NjY1LCJleHAiOjE1ODY4MjI0NjUsImlhdCI6MTU4NjY0OTY2NX0.IM_hRtRmPRXat_vQasUKhvCkzImc6W9vz369oi3CQTI");
        userTO.setEmail("test2@gmail.com");
        userTO.setPasswd("test");


        PropertyServerApi propertyServerApi=new PropertyServerApi();
        propertyServerApi.updatePropertyStatusRented(userTO.getToken(), propertyTO, new Callback<PropertyTO>() {
            @Override
            public void onResponse(Call<PropertyTO> call, Response<PropertyTO> response) {
                Assert.assertEquals(200,response.code());
            }

            @Override
            public void onFailure(Call<PropertyTO> call, Throwable t) {

            }
        });
    }
}
