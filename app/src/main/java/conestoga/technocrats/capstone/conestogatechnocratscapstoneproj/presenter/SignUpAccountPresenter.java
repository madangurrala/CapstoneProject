package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ISignUpAccountContract;

public class SignUpAccountPresenter
{
    private Context ctx;
    private ISignUpAccountContract iSignUpAccountContract;
    private UserBL userBL;
    public SignUpAccountPresenter(Context ctx,ISignUpAccountContract iSignUpAccountContract)
    {
        this.ctx=ctx;
        this.iSignUpAccountContract=iSignUpAccountContract;
        userBL=new UserBL(this.ctx);
    }

    public void registerUser(UserTO userTO)
    {
        if(userBL.registerLoginAccountSP(userTO))
        {
            iSignUpAccountContract.signUpStatus(true,userTO);
        }
    }

    public void validateUserData(UserTO userTO)
    {
        iSignUpAccountContract.userDataValidationStatus(true,userTO);
    }
}
