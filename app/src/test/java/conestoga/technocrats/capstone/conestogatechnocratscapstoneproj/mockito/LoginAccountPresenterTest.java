package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.mockito;

//https://dzone.com/articles/how-to-use-mockito-in-android

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.LoginAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ILoginContract;

public class LoginAccountPresenterTest
{
    private LoginAccountPresenter loginAccountPresenter;
    private UserTO userTO;

    @Mock
    private Context context;
    @Mock
    private ILoginContract iLoginContract;

    @Before
    public void beforeTest()
    {
        MockitoAnnotations.initMocks(this);
        loginAccountPresenter=new LoginAccountPresenter(context,iLoginContract);
        userTO=new UserTO();
    }

    @Test
    public void login()
    {
        userTO.setEmail("test2@gmail.com");
        userTO.setPasswd("test");
        loginAccountPresenter.loginUser(userTO);
        Mockito.verify(iLoginContract).userLoginStatus(ArgumentMatchers.eq(true), ArgumentMatchers.isNotNull());
    }
}
