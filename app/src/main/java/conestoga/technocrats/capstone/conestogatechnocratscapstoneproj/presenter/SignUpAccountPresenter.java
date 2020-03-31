package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.sp.LoginAccountSP;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.UserServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ILoginContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ISignUpAccountContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpAccountPresenter
{
    private Context ctx;
    private UserServerApi userServerApi;
    private UserBL userBL;
    private ISignUpAccountContract iSignUpAccountContract;
    public SignUpAccountPresenter(Context ctx,ISignUpAccountContract iSignUpAccountContract)
    {
        this.ctx=ctx;
        userServerApi=new UserServerApi(null);
        userBL=new UserBL(this.ctx);
        this.iSignUpAccountContract=iSignUpAccountContract;
    }

    public void registerUser(UserTO userTO)
    {
        userServerApi.register(userTO, new Callback<UserTO>() {
            @Override
            public void onResponse(Call<UserTO> call, Response<UserTO> response) {
                if(response.code()!=200)
                {
                    iSignUpAccountContract.signUpStatus(false,userTO);
                    return;
                }
                UserTO registeredUserTO=response.body();
               new AsyncTaskActions(userBL,iSignUpAccountContract).execute(registeredUserTO);
            }

            @Override
            public void onFailure(Call<UserTO> call, Throwable t) {
                iSignUpAccountContract.signUpStatus(false,null);
            }
        });
    }

    public void validateUserData(UserTO userTO)
    {
        iSignUpAccountContract.userDataValidationStatus(true,userTO);
    }


    private static class AsyncTaskActions extends AsyncTask<UserTO,Void,Void>
    {
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<ISignUpAccountContract> iSignUpAccountContractWeakReference;

        public AsyncTaskActions(UserBL userBL,ISignUpAccountContract iSignUpAccountContract)
        {
            userBLWeakReference=new WeakReference<>(userBL);
            iSignUpAccountContractWeakReference=new WeakReference<>(iSignUpAccountContract);
        }

        @Override
        protected Void doInBackground(UserTO... userTOS)
        {
            UserTO registeredUserTO=userTOS[0];
            userBLWeakReference.get().registerLoginAccountSP(registeredUserTO);
            userBLWeakReference.get().registerUser(registeredUserTO);
            iSignUpAccountContractWeakReference.get().signUpStatus(true,registeredUserTO);
            return null;
        }
    }
}
