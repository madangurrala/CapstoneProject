package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public interface IPropertyDetailsContract
{
    public void setUserDetails(UserTO userTO);
    public void setOwnerPropertyData(UserTO userTO, PropertyTO propertyTO);
    public void updatePropertyData(boolean status,UserTO userTO, PropertyTO propertyTO);
    public void setNewAppointment(boolean status);
}
