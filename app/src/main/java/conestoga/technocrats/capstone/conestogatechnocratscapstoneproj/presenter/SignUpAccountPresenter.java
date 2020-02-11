package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ISignUpAccountContract;

public class SignUpAccountPresenter
{
    private ISignUpAccountContract iSignUpAccountContract;
    public SignUpAccountPresenter(ISignUpAccountContract iSignUpAccountContract)
    {
        this.iSignUpAccountContract=iSignUpAccountContract;
    }

    public void registerUser(UserTO userTO)
    {
        iSignUpAccountContract.signUpStatus(true,userTO);
    }

    public void validateUserData(UserTO userTO)
    {
        iSignUpAccountContract.userDataValidationStatus(true,userTO);
    }
}
