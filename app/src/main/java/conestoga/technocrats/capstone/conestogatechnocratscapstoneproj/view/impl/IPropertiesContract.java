package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;

public interface IPropertiesContract
{
    public void fillPropertiesRecycleView(List<PropertyTO> propertyTOS);
    public void addProperty(boolean status);
}
