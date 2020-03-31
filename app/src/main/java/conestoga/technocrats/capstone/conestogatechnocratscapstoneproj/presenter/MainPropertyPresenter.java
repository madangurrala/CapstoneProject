package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.PropertyServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IPropertiesContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPropertyPresenter {
    private Context ctx = null;
    private PropertyServerApi propertyServerApi;
    private UserBL userBL;
    private IPropertiesContract iPropertiesContract;

    public MainPropertyPresenter(Context ctx, IPropertiesContract iPropertiesContract) {
        this.ctx = ctx;
        userBL = new UserBL(ctx);
        propertyServerApi = new PropertyServerApi();
        this.iPropertiesContract = iPropertiesContract;
    }

    public void getPropertiesList() {
        new AsyncTaskGetAction(userBL,iPropertiesContract,propertyServerApi).execute();
    }

    public void addPropertyValidation(PropertyTO propertyTO)
    {
        if(propertyTO!=null)
        {
            addProperty(propertyTO);
        }
    }
    private void addProperty(PropertyTO propertyTO)
    {
        new AsyncTaskAddAction(userBL,iPropertiesContract,propertyServerApi).execute(propertyTO);
    }


    private static class AsyncTaskGetAction extends AsyncTask<Void,Void,Void>
    {
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<IPropertiesContract> iPropertiesContractWeakReference;
        private WeakReference<PropertyServerApi> propertyServerApiWeakReference;
        public AsyncTaskGetAction(UserBL userBL,IPropertiesContract iPropertiesContract,PropertyServerApi propertyServerApi)
        {
            userBLWeakReference=new WeakReference<>(userBL);
            iPropertiesContractWeakReference=new WeakReference<>(iPropertiesContract);
            propertyServerApiWeakReference=new WeakReference<>(propertyServerApi);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            UserTO userTO = userBLWeakReference.get().fetchLoginAccountSP();
            if (userTO == null || userTO.getEmail() == null) {
                iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                return null;
            }
            userTO = userBLWeakReference.get().fetchUser(userTO.getEmail());
            if (userTO == null || userTO.getToken() == null) {
                iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                return null;
            }
            propertyServerApiWeakReference.get().getProperties(userTO.getToken(), new Callback<List<PropertyTO>>() {
                @Override
                public void onResponse(Call<List<PropertyTO>> call, Response<List<PropertyTO>> response) {
                    if(response.code()!=200)
                    {
                        iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                        return;
                    }
                    iPropertiesContractWeakReference.get().fillPropertiesRecycleView(response.body());
                    //todo register all the available properties in local data base
                }

                @Override
                public void onFailure(Call<List<PropertyTO>> call, Throwable t) {
                    iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                }
            });
            return null;
        }
    }

    private static class AsyncTaskAddAction extends AsyncTask<PropertyTO,Void,Void>
    {
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<IPropertiesContract> iPropertiesContractWeakReference;
        private WeakReference<PropertyServerApi> propertyServerApiWeakReference;
        public AsyncTaskAddAction(UserBL userBL,IPropertiesContract iPropertiesContract,PropertyServerApi propertyServerApi)
        {
            userBLWeakReference=new WeakReference<>(userBL);
            iPropertiesContractWeakReference=new WeakReference<>(iPropertiesContract);
            propertyServerApiWeakReference=new WeakReference<>(propertyServerApi);
        }
        @Override
        protected Void doInBackground(PropertyTO... propertyTOs) {
            UserTO userTO = userBLWeakReference.get().fetchLoginAccountSP();
            if (userTO == null || userTO.getEmail() == null) {
                iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                return null;
            }
            userTO = userBLWeakReference.get().fetchUser(userTO.getEmail());
            if (userTO == null || userTO.getToken() == null) {
                iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                return null;
            }
            propertyServerApiWeakReference.get().registerProperty(userTO.getToken(), propertyTOs[0], new Callback<PropertyTO>() {
                @Override
                public void onResponse(Call<PropertyTO> call, Response<PropertyTO> response) {
                    if(response.code()!=200)
                    {
                        iPropertiesContractWeakReference.get().addProperty(false);
                        return;
                    }
                    iPropertiesContractWeakReference.get().addProperty(true);
                    //todo register all the available property in local data base
                }

                @Override
                public void onFailure(Call<PropertyTO> call, Throwable t) {
                    iPropertiesContractWeakReference.get().addProperty(false);
                }
            });
            return null;
        }
    }
}
