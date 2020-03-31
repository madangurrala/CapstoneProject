package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

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

    public void updateUserProfile()
    {

    }


    public void getUserProfile()
    {
        new AsyncTaskGetProfileAction(userBL,iProfileContract).execute();
    }

    private static class AsyncTaskGetProfileAction extends AsyncTask<Void,Void,UserTO>
    {
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<IProfileContract> iProfileContractWeakReference;

        public AsyncTaskGetProfileAction(UserBL userBL,IProfileContract iProfileContract)
        {
            userBLWeakReference=new WeakReference<>(userBL);
            iProfileContractWeakReference=new WeakReference<>(iProfileContract);
        }

        @Override
        protected UserTO doInBackground(Void... voids)
        {
            UserTO userTO=userBLWeakReference.get().fetchLoginAccountSP();
            if(userTO==null || userTO.getEmail()==null)
            {
                return null;
            }
            userTO=userBLWeakReference.get().fetchUser(userTO.getEmail());
            if(userTO==null || userTO.getToken()==null)
            {
                return null;
            }
            return userTO;
        }

        @Override
        protected void onPostExecute(UserTO userTO) {
            super.onPostExecute(userTO);
            iProfileContractWeakReference.get().setUserProfileData(userTO);
        }
    }
}
