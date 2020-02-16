package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;

import androidx.fragment.app.Fragment;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.LoginFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.SignUpAccountFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IAskAccountContract;

public class AskAccountPresenter
{
    private Context ctx=null;
    private IAskAccountContract iAskAccountContract=null;
    private UserBL userBL=null;

    public AskAccountPresenter(Context ctx,IAskAccountContract iAskAccountContract)
    {
       this.ctx=ctx;
       this.iAskAccountContract=iAskAccountContract;
        userBL=new UserBL(this.ctx);
    }

    public void showRightFragment(Fragment fragment,String title)
    {

        if(fragment!=null)
        {
            iAskAccountContract.showFragment(fragment,title);
            return;
        }
        UserTO userTO=userBL.fetchLoginAccountSP();
        if(userTO==null || userTO.getEmail()==null)
        {
            iAskAccountContract.showFragment(new SignUpAccountFragment(),ctx.getResources().getString(R.string.sign_up));
        }
        else
        {
            iAskAccountContract.showFragment(new LoginFragment(),ctx.getResources().getString(R.string.login));
        }


    }
}
