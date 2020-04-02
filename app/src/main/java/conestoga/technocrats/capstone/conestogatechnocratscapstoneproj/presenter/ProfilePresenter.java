package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.UserServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.ProfileFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.LoginFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.SignUpAccountFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IAskAccountContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IProfileContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    private Context ctx=null;
    private String token;
    private UserBL userBL=null;
    private IProfileContract iProfileContract=null;
    private UserServerApi userServerApi;

    public ProfilePresenter(Context ctx,IProfileContract iProfileContract)
    {
        this.ctx=ctx;
        this.iProfileContract=iProfileContract;
        userBL=new UserBL(this.ctx);
    }

    public void updateUserProfile(UserTO userTO)
    {
        if(userTO==null || userTO.getToken()==null)
        {
            iProfileContract.updateUserProfileData(false,userTO);
            return;
        }
        userServerApi=new UserServerApi(userTO.getToken());
        userServerApi.update(userTO, new Callback<UserTO>() {
            @Override
            public void onResponse(Call<UserTO> call, Response<UserTO> response) {
                if(response.code()!=200)
                {
                    iProfileContract.updateUserProfileData(false,userTO);
                    return;
                }
                UserTO updateUserTO=response.body();
                updateUserTO.setUserId(userTO.getUserId());
                updateUserTO.setToken(userTO.getToken());
                iProfileContract.updateUserProfileData(true,updateUserTO);
                new AsyncTaskUserUpdateAction(userBL).execute(updateUserTO);
            }

            @Override
            public void onFailure(Call<UserTO> call, Throwable t) {
                iProfileContract.updateUserProfileData(false,userTO);
            }
        });
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

    private static class AsyncTaskUserUpdateAction extends AsyncTask<UserTO,Void,Void>
    {
        private WeakReference<UserBL> userBLWeakReference;

        public AsyncTaskUserUpdateAction(UserBL userBL)
        {
            userBLWeakReference=new WeakReference<>(userBL);
        }

        @Override
        protected Void doInBackground(UserTO... userTOS)
        {
            UserTO userTO=userTOS[0];
            userBLWeakReference.get().registerLoginAccountSP(userTO);
            userBLWeakReference.get().updateUser(userTO);
            return null;
        }
    }
}
