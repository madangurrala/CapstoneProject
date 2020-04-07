package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.AppointmentServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.utils.FirebaseUtil;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IAppointmentsContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatListContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentListPresenter
{
    private Context ctx=null;
    private UserTO userTO;
    private UserBL userBL;
    private IAppointmentsContract iAppointmentsContract;
    private AppointmentServerApi appointmentServerApi;

    public AppointmentListPresenter(Context ctx, IAppointmentsContract iAppointmentsContract)
    {
       this.ctx=ctx;
       this.userBL=new UserBL(ctx);
       this.iAppointmentsContract=iAppointmentsContract;
       this.appointmentServerApi=new AppointmentServerApi();

    }

    public void getAppointmentsList()
    {
        new AsyncTaskActions(userBL,iAppointmentsContract,appointmentServerApi).execute();
    }

    public void setUserTO(UserTO userTO)
    {
        this.userTO=userTO;
    }

    public void acceptAppointmentRequest(Activity activity,AppointmentTO appointmentTO)
    {
        MessageTO messageTO=new MessageTO();
        messageTO.setSenderId((int) userTO.getId());
        messageTO.setReceiverId((int) appointmentTO.getPeerId());
        messageTO.setMessage("Hello, I got your appointment request, so, let's start.....");
        messageTO.setRegisterDate(new Date().getTime());
        FirebaseUtil.getInstance(ctx).sendMessage(activity, messageTO, new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                iAppointmentsContract.acceptAppointmentRequestResult(true);
            }
        });
    }

    private static class AsyncTaskActions extends AsyncTask<Void,Void,UserTO>
    {
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<IAppointmentsContract> iAppointmentsContractWeakReference;
        private WeakReference<AppointmentServerApi> appointmentServerApiWeakReference;

        public AsyncTaskActions(UserBL userBL,IAppointmentsContract iAppointmentsContract,AppointmentServerApi appointmentServerApi)
        {
            userBLWeakReference=new WeakReference<>(userBL);
            iAppointmentsContractWeakReference=new WeakReference<>(iAppointmentsContract);
            appointmentServerApiWeakReference=new WeakReference<>(appointmentServerApi);
        }

        @Override
        protected UserTO doInBackground(Void... voids)
        {
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
                iAppointmentsContractWeakReference.get().fillAppointmentsRecycleView(null,null);
                return;
            }

            appointmentServerApiWeakReference.get().getAllAppointments(userTO.getToken(), new Callback<List<AppointmentTO>>() {
                @Override
                public void onResponse(Call<List<AppointmentTO>> call, Response<List<AppointmentTO>> response) {
                    if(response.code()!=200)
                    {
                        iAppointmentsContractWeakReference.get().fillAppointmentsRecycleView(userTO,null);
                        return;
                    }
                    iAppointmentsContractWeakReference.get().fillAppointmentsRecycleView(userTO,response.body());
                    //todo register all the available appointments in the local data base
                }

                @Override
                public void onFailure(Call<List<AppointmentTO>> call, Throwable t) {
                    iAppointmentsContractWeakReference.get().fillAppointmentsRecycleView(userTO,null);
                }
            });
        }
    }
}
