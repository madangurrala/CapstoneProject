package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.user;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.SignUpAccountPresenter;
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
                Assert.assertTrue(status);
            }

            @Override
            public void userDataValidationStatus(boolean status, UserTO userTO) {

            }
        });
        signUpAccountPresenter.registerUser(userTO);


    }
}
