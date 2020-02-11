package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ILoginContract;

public class LoginAccountPresenter
{
    private ILoginContract iLoginContract=null;
    public LoginAccountPresenter(ILoginContract iLoginContract)
    {
        this.iLoginContract=iLoginContract;
    }


    public void validateUserData(UserTO userTO)
    {
        iLoginContract.isUserDataValid(true,userTO);
    }

    public void loginUser(UserTO userTO)
    {
        iLoginContract.userLoginStatus(true,userTO);
    }

}
