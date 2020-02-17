package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IAppointmentsContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatListContract;

public class AppointmentListPresenter
{
    private Context ctx=null;
    private IAppointmentsContract iAppointmentsContract;

    public AppointmentListPresenter(Context ctx, IAppointmentsContract iAppointmentsContract)
    {
       this.ctx=ctx;
       this.iAppointmentsContract=iAppointmentsContract;
    }

    public void getAppointmentsList()
    {
        // TODO: 16/02/20 Update this part later
        List<AppointmentTO> appointmentTOS=new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            AppointmentTO appointmentTO=new AppointmentTO();
            appointmentTOS.add(appointmentTO);
        }
        iAppointmentsContract.fillAppointmentsRecycleView(appointmentTOS);
    }
}
