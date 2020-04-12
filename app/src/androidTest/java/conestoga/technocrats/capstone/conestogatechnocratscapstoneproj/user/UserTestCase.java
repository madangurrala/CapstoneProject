package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.user;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.LoginAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.ProfilePresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.SignUpAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ILoginContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IProfileContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ISignUpAccountContract;

@RunWith(AndroidJUnit4.class)
public class UserTestCase
{
    @Test
    public void createAccountTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserTO userTO=new UserTO();
        userTO.setName("Name Test");
        userTO.setFamily("Family Test");
        userTO.setEmail(String.format("%s%d%s","user.test",new Random().nextInt(500),"@gmail.com"));
        userTO.setPhone("123456789");
        userTO.setPasswd("pass123");

        SignUpAccountPresenter signUpAccountPresenter=new SignUpAccountPresenter(appContext, new ISignUpAccountContract() {
            @Override
            public void signUpStatus(boolean status, UserTO userTO) {
                Assert.assertFalse(status);
            }

            @Override
            public void userDataValidationStatus(boolean status, UserTO userTO) {

            }
        });
        signUpAccountPresenter.registerUser(userTO);
    }


    @Test
    public void loginAccountTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserTO userTO=new UserTO();
        userTO.setEmail("test2@gmail.com");
        userTO.setPasswd("test");

        LoginAccountPresenter loginAccountPresenter=new LoginAccountPresenter(appContext, new ILoginContract() {
            @Override
            public void isUserDataValid(boolean status, UserTO userTO) {
                Assert.assertTrue(status);
            }

            @Override
            public void userLoginStatus(boolean status, UserTO userTO) {

            }
        });
        loginAccountPresenter.loginUser(userTO);
    }


    @Test
    public void updateAccountTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserTO userTO=new UserTO();
        userTO.setId(11);
        userTO.setPhone("1122334455");
        userTO.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InRlc3QyQGdtYWlsLmNvbSIsImVtYWlsIjoiQWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3ZlcnNpb24iOiJWMy4xIiwibmJmIjoxNTg2NjQ5NjY1LCJleHAiOjE1ODY4MjI0NjUsImlhdCI6MTU4NjY0OTY2NX0.IM_hRtRmPRXat_vQasUKhvCkzImc6W9vz369oi3CQTI");
        userTO.setEmail("test2@gmail.com");
        userTO.setPasswd("test");

        ProfilePresenter profilePresenter=new ProfilePresenter(appContext, new IProfileContract() {
            @Override
            public void setUserProfileData(UserTO userTO) {

            }

            @Override
            public void updateUserProfileData(boolean status, UserTO userTO) {
                Assert.assertTrue(status);
            }
        });
        profilePresenter.updateUserProfile(userTO);
    }


    @Test
    public void viewAccountTest()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserTO userTO=new UserTO();
        userTO.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InRlc3QyQGdtYWlsLmNvbSIsImVtYWlsIjoiQWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3ZlcnNpb24iOiJWMy4xIiwibmJmIjoxNTg2NjQ5NjY1LCJleHAiOjE1ODY4MjI0NjUsImlhdCI6MTU4NjY0OTY2NX0.IM_hRtRmPRXat_vQasUKhvCkzImc6W9vz369oi3CQTI");
        userTO.setEmail("test2@gmail.com");
        userTO.setPasswd("test");

        ProfilePresenter profilePresenter=new ProfilePresenter(appContext, new IProfileContract() {
            @Override
            public void setUserProfileData(UserTO userTO) {
                Assert.assertNotNull(userTO);
            }

            @Override
            public void updateUserProfileData(boolean status, UserTO userTO) {
            }
        });
        profilePresenter.getUserProfile();
    }
}
