package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.mockito;

//https://www.youtube.com/watch?v=d2KwvXQgQx4&t=1219s

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public class UserToTest
{

    @Test
    public void test1()
    {
        UserTO userTO=Mockito.mock(UserTO.class);
        Mockito.when(userTO.getName()).thenReturn("Farshad");
        Assert.assertEquals("Farshad",userTO.getName());
    }

    @Test
    public void test2()
    {
        UserTO userTO=Mockito.mock(UserTO.class);
        Mockito.when(userTO.getName()).thenReturn("Farshad").thenReturn("Madan");
        Assert.assertEquals("Farshad",userTO.getName());
        Assert.assertEquals("Madan",userTO.getName());
    }


    //@Test()
    @Test(timeout = 1000)
    public void test3()
    {
        List<String> stringList=Mockito.mock(List.class);
        Mockito.when(stringList.get(0)).thenReturn("Farshad");
        Mockito.when(stringList.get(1)).thenReturn("Madan");
        Assert.assertEquals("Farshad",stringList.get(0));
        Assert.assertEquals("Madan",stringList.get(1));
    }

    @Test()
    public void test4()
    {
        List<String> stringList=Mockito.mock(List.class);
        Mockito.when(stringList.get(Mockito.anyInt())).thenReturn("Something");
        Assert.assertNotEquals(null,stringList.get(0));
        Assert.assertNotEquals(null,stringList.get(1));
        Assert.assertNotEquals(null,stringList.get(2));
    }

    @Test(expected = RuntimeException.class)
    public void test5()
    {
        List<String> stringList=Mockito.mock(List.class);
        Mockito.when(stringList.get(Mockito.anyInt())).thenThrow(RuntimeException.class);
        stringList.get(0);
        //stringList.get(Mockito.anyInt());
    }







   /* private LoginAccountPresenter loginAccountPresenter;
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
    }*/
}
