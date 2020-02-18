package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.LoginFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.SignUpAccountFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IAskAccountContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IPropertiesContract;

public class MainPropertyPresenter
{
    private Context ctx=null;
    private IPropertiesContract iPropertiesContract;

    public MainPropertyPresenter(Context ctx, IPropertiesContract iPropertiesContract)
    {
       this.ctx=ctx;
       this.iPropertiesContract=iPropertiesContract;
    }

    public void getPropertiesList()
    {
        // TODO: 16/02/20 Update this part later
        List<PropertyTO> propertyTOS=new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            PropertyTO propertyTO=new PropertyTO();
            propertyTO.setId(i);
            propertyTO.setTitle("Property Title "+i);
            propertyTO.setSmallImagePath("https://www.theplancollection.com/Upload/Plans/SubCategory/240216120038_Plan1011874MainImage_28_9_2015_13_600_400.jpg");
            propertyTO.setAddress("Location "+i);
            propertyTOS.add(propertyTO);
        }
        iPropertiesContract.fillPropertiesRecycleView(propertyTOS);
    }
}
