package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public interface IAppointmentsContract
{
    public void acceptAppointmentRequestResult(boolean status);
    public void fillAppointmentsRecycleView(UserTO userTO,List<AppointmentTO> appointmentTOS);
}
