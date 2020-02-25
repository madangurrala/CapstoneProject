package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;

import androidx.fragment.app.Fragment;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.ProfileFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.LoginFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.SignUpAccountFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IAskAccountContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IProfileContract;

public class ProfilePresenter {
    private Context ctx=null;
    private IProfileContract iProfileContract=null;
    private UserBL userBL=null;

    public ProfilePresenter(Context ctx,IProfileContract iProfileContract)
    {
        this.ctx=ctx;
        this.iProfileContract=iProfileContract;
        userBL=new UserBL(this.ctx);
    }

    public void showRightFragment(Fragment fragment)
    {
        if(fragment!=null)
        {
            iProfileContract.showFragment(fragment);
            return;
        }
        UserTO userTO=userBL.fetchLoginAccountSP();
        if(userTO==null || userTO.getEmail()==null)
        {
            iProfileContract.showFragment(new LoginFragment());
        }
        else
        {
            iProfileContract.showFragment(new ProfileFragment());
        }


    }

}
