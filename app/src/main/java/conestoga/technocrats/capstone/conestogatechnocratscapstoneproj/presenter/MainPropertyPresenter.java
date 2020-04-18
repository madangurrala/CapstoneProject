package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import java.lang.ref.WeakReference;
import java.util.Date;
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

    private LocationManager locationManager;
    private LocationListener locationListener;

    public MainPropertyPresenter(Context ctx, IPropertiesContract iPropertiesContract) {
        this.ctx = ctx;
        userBL = new UserBL(ctx);
        propertyServerApi = new PropertyServerApi();
        this.iPropertiesContract = iPropertiesContract;
    }

    public void getPropertiesList() {
        new AsyncTaskGetUserAction(userBL, iPropertiesContract, propertyServerApi).execute();
    }

    private void requestGpsProviderSettings() {
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    public void isGpsServiceAvailable() {
        boolean status = true;
        locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            status = false;
            requestGpsProviderSettings();
        }
        iPropertiesContract.isGpsServiceAvailable(status);
    }

    public void updateDeviceCurrentLocation() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                iPropertiesContract.updateProperLocation(location.getLatitude(),location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null);
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


    private static class AsyncTaskGetUserAction extends AsyncTask<Void,Void,UserTO>
    {
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<IPropertiesContract> iPropertiesContractWeakReference;
        private WeakReference<PropertyServerApi> propertyServerApiWeakReference;
        public AsyncTaskGetUserAction(UserBL userBL,IPropertiesContract iPropertiesContract,PropertyServerApi propertyServerApi)
        {
            userBLWeakReference=new WeakReference<>(userBL);
            iPropertiesContractWeakReference=new WeakReference<>(iPropertiesContract);
            propertyServerApiWeakReference=new WeakReference<>(propertyServerApi);
        }
        @Override
        protected UserTO doInBackground(Void... voids) {
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
            if(userTO==null)
            {
                iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                return;
            }
            propertyServerApiWeakReference.get().getProperties(userTO.getToken(), new Callback<List<PropertyTO>>() {
                @Override
                public void onResponse(Call<List<PropertyTO>> call, Response<List<PropertyTO>> response)
                {
                    if(response.code()!=200)
                    {
                        iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                        return;
                    }
                    iPropertiesContractWeakReference.get().fillPropertiesRecycleView(response.body());
                }

                @Override
                public void onFailure(Call<List<PropertyTO>> call, Throwable t) {
                    iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                }
            });
        }
    }

    private static class AsyncTaskAddAction extends AsyncTask<PropertyTO,Void,UserTO>
    {
        private PropertyTO propertyTO;
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
        protected UserTO doInBackground(PropertyTO... propertyTOs) {
            UserTO userTO = userBLWeakReference.get().fetchLoginAccountSP();
            if (userTO == null || userTO.getEmail() == null) {
                return null;
            }
            userTO = userBLWeakReference.get().fetchUser(userTO.getEmail());
            if (userTO == null || userTO.getToken() == null) {
                return null;
            }
            propertyTO=propertyTOs[0];
            propertyTO.setUserId(userTO.getId());
            propertyTO.setUser(userTO.getName());
            propertyTO.setRegisterDate(new Date().getTime());
            propertyTO.setRate(0.0f);
            propertyTO.setViewCount(0);
            return userTO;
        }

        @Override
        protected void onPostExecute(UserTO userTO) {
            super.onPostExecute(userTO);
            if(userTO==null)
            {
                iPropertiesContractWeakReference.get().fillPropertiesRecycleView(null);
                return;
            }
            propertyServerApiWeakReference.get().registerProperty(userTO.getToken(),propertyTO, new Callback<PropertyTO>() {
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
        }
    }
}
