package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Patterns;

import java.lang.ref.WeakReference;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.sp.LoginAccountSP;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.UserServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ILoginContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAccountPresenter
{
    private UserServerApi userServerApi;
    private UserBL userBL;
    private ILoginContract iLoginContract=null;
    public LoginAccountPresenter(Context context,ILoginContract iLoginContract)
    {
        userServerApi=new UserServerApi(null);
        userBL=new UserBL(context);
        this.iLoginContract=iLoginContract;
    }


    public void validateUserData(UserTO userTO)
    {
        boolean isaValid=true;
        if(userTO==null)
        {
            iLoginContract.isUserDataValid(false,userTO);
            return;
        }
        if(TextUtils.isEmpty(userTO.getEmail()) || !Patterns.EMAIL_ADDRESS.matcher(userTO.getEmail()).matches())
        {
            isaValid=false;
            iLoginContract.loginEmailNotValid();
        }
        if(TextUtils.isEmpty(userTO.getPasswd()) || userTO.getPasswd().trim().length()<5)
        {
            isaValid=false;
            iLoginContract.loginPassNotValid();
        }
        iLoginContract.isUserDataValid(isaValid,userTO);
    }

    public void loginUser(UserTO userTO)
    {
        userServerApi.login(userTO, new Callback<UserTO>() {
            @Override
            public void onResponse(Call<UserTO> call, Response<UserTO> response) {
                if(response.code()!=200)
                {
                    iLoginContract.userLoginStatus(false,null);
                    return;
                }
                UserTO loginUserTO=response.body();
                new AsyncTaskActions(userBL,iLoginContract).execute(loginUserTO);
            }

            @Override
            public void onFailure(Call<UserTO> call, Throwable t) {
                iLoginContract.userLoginStatus(false,null);
            }
        });
    }

    private static class AsyncTaskActions extends AsyncTask<UserTO,Void,Void>
    {
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<ILoginContract> iLoginContractWeakReference;

        public AsyncTaskActions(UserBL userBL,ILoginContract iLoginContract)
        {
            userBLWeakReference=new WeakReference<>(userBL);
            iLoginContractWeakReference=new WeakReference<>(iLoginContract);
        }

        @Override
        protected Void doInBackground(UserTO... userTOS)
        {
            UserTO loginUserTO=userTOS[0];
            userBLWeakReference.get().registerLoginAccountSP(loginUserTO);
            userBLWeakReference.get().registerUser(loginUserTO);
            iLoginContractWeakReference.get().userLoginStatus(true,loginUserTO);
            return null;
        }
    }

}
