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
        new AsyncTaskActions(userBL,iPropertiesContract,propertyServerApi).execute();
    }

    private static class AsyncTaskActions extends AsyncTask<Void,Void,Void>
    {
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<IPropertiesContract> iPropertiesContractWeakReference;
        private WeakReference<PropertyServerApi> propertyServerApiWeakReference;
        public AsyncTaskActions(UserBL userBL,IPropertiesContract iPropertiesContract,PropertyServerApi propertyServerApi)
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
                    System.out.println(response.code());

                }

                @Override
                public void onFailure(Call<List<PropertyTO>> call, Throwable t) {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+t.getMessage());
                    iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                }
            });
            return null;
        }
    }
}
