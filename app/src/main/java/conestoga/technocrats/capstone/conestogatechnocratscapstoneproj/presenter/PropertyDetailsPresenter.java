package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.Date;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.AppointmentServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.PropertyServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.UserServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IPropertyDetailsContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyDetailsPresenter {
    private Context ctx;
    private UserBL userBL;
    private UserTO userTO;
    private IPropertyDetailsContract iPropertyDetailsContract;

    public PropertyDetailsPresenter(Context ctx, IPropertyDetailsContract iPropertyDetailsContract) {
        this.ctx = ctx;
        this.iPropertyDetailsContract = iPropertyDetailsContract;
        this.userBL = new UserBL(ctx);
    }

    public void getUserDetails(PropertyTO propertyTO)
    {
        new AsyncTaskGetUserAction(propertyTO, userBL, iPropertyDetailsContract).execute();
    }

    public void getPropertyOwnerDetails(UserTO userTO,PropertyTO propertyTO)
    {
        if(userTO==null || propertyTO==null)
        {
            iPropertyDetailsContract.setOwnerPropertyData(null,null);
            return;
        }
        UserServerApi userServerApi=new UserServerApi(userTO.getToken());
        userServerApi.getUserById(propertyTO.getUserId(), new Callback<UserTO>() {
            @Override
            public void onResponse(Call<UserTO> call, Response<UserTO> response) {
                if(response.code()!=200)
                {
                    iPropertyDetailsContract.setOwnerPropertyData(null,null);
                    return;
                }
                iPropertyDetailsContract.setOwnerPropertyData(response.body(),propertyTO);
            }

            @Override
            public void onFailure(Call<UserTO> call, Throwable t) {
                iPropertyDetailsContract.setOwnerPropertyData(null,null);
            }
        });
    }


    public void updatePropertyStatusRented(UserTO userTO,PropertyTO propertyTO)
    {
        PropertyServerApi propertyServerApi=new PropertyServerApi();
        propertyServerApi.updatePropertyStatusRented(userTO.getToken(), propertyTO, new Callback<PropertyTO>() {
            @Override
            public void onResponse(Call<PropertyTO> call, Response<PropertyTO> response) {
                if(response.code()!=200)
                {
                    iPropertyDetailsContract.updatePropertyData(false,userTO,propertyTO);
                    return;
                }
                iPropertyDetailsContract.updatePropertyData(true,userTO,propertyTO);
            }

            @Override
            public void onFailure(Call<PropertyTO> call, Throwable t) {
                iPropertyDetailsContract.updatePropertyData(false,userTO,propertyTO);
            }
        });
    }

    public void requestAppointment(UserTO userTO,PropertyTO propertyTO)
    {
        AppointmentServerApi appointmentServerApi=new AppointmentServerApi();
        appointmentServerApi.addAppointment(userTO.getToken(), propertyTO.getId(), new Date().getTime(), new Callback<AppointmentTO>() {
            @Override
            public void onResponse(Call<AppointmentTO> call, Response<AppointmentTO> response) {
                if(response.code()!=200)
                {
                    iPropertyDetailsContract.setNewAppointment(false);
                    return;
                }
                iPropertyDetailsContract.setNewAppointment(true);
            }

            @Override
            public void onFailure(Call<AppointmentTO> call, Throwable t) {
                iPropertyDetailsContract.setNewAppointment(false);
            }
        });
    }

    private static class AsyncTaskGetUserAction extends AsyncTask<Void, Void, UserTO> {
        private WeakReference<PropertyTO> propertyTOWeakReference;
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<IPropertyDetailsContract> iPropertyDetailsContractWeakReference;

        public AsyncTaskGetUserAction(PropertyTO propertyTO, UserBL userBL, IPropertyDetailsContract iPropertyDetailsContract) {
            propertyTOWeakReference = new WeakReference<>(propertyTO);
            userBLWeakReference = new WeakReference<>(userBL);
            iPropertyDetailsContractWeakReference = new WeakReference<>(iPropertyDetailsContract);
        }

        @Override
        protected UserTO doInBackground(Void... voids) {
            if (propertyTOWeakReference.get() == null) {
                return null;
            }
            UserTO userTO = userBLWeakReference.get().fetchLoginAccountSP();
            if (userTO == null || userTO.getEmail() == null) {
                return null;
            }
            userTO = userBLWeakReference.get().fetchUser(userTO.getEmail());
            if (userTO == null || userTO.getToken() == null) {
                return null;
            }
            return userTO;
        }

        @Override
        protected void onPostExecute(UserTO userTO) {
            super.onPostExecute(userTO);
            iPropertyDetailsContractWeakReference.get().setUserDetails(userTO);
        }
    }
}
