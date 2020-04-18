package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;

public interface IPropertiesContract
{
    public void isGpsServiceAvailable(boolean status);
    public void updateProperLocation(double lat,double lng);
    public void fillPropertiesRecycleView(List<PropertyTO> propertyTOS);
    public void addProperty(boolean status);
}
