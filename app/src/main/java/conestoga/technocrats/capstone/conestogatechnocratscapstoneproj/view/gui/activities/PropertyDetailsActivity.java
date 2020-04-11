package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.PropertyDetailsPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.root.AppImagePresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IPropertyDetailsContract;

public class PropertyDetailsActivity extends AppCompatActivity implements IPropertyDetailsContract
{
    private PropertyTO propertyTO;
    private UserTO userTO;
    private PropertyDetailsPresenter propertyDetailsPresenter;
    private AppImagePresenter appImagePresenter;


    @BindView(R.id.imgOwner)
    public ImageView imgOwner;
    @BindView(R.id.txtOwnerName)
    public MaterialTextView txtOwnerName;
    @BindView(R.id.txtOwnerPhone)
    public MaterialTextView txtOwnerPhone;
    @BindView(R.id.txtOwnerEmail)
    public MaterialTextView txtOwnerEmail;
    @BindView(R.id.txtPropertySize)
    public MaterialTextView txtPropertySize;
    @BindView(R.id.txtPropertyStatus)
    public MaterialTextView txtPropertyStatus;
    @BindView(R.id.txtPropertyPrice)
    public MaterialTextView txtPropertyPrice;
    @BindView(R.id.txtPropertyRegisterDate)
    public MaterialTextView txtPropertyRegisterDate;
    @BindView(R.id.txtPropertyDesc)
    public MaterialTextView txtPropertyDesc;
    @BindView(value = R.id.btnShowMap)
    public MaterialButton btnShowMap;
    @BindView(value = R.id.btnPropertyRented)
    public MaterialButton btnPropertyRented;
    @BindView(value = R.id.btnRequestAppointment)
    public MaterialButton btnRequestAppointment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        ButterKnife.bind(this);
        propertyTO=(PropertyTO)getIntent().getParcelableExtra("property");
        propertyDetailsPresenter=new PropertyDetailsPresenter(getApplicationContext(),this);
        appImagePresenter=new AppImagePresenter();
        propertyDetailsPresenter.getUserDetails(propertyTO);
    }

    @OnClick(value = {R.id.btnShowMap,R.id.btnPropertyRented,R.id.btnRequestAppointment})
    public void doClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnShowMap:
            {
                Intent intent=new Intent(this,PropertyMapActivity.class);
                intent.putExtra(PropertyMapActivity.KEY.LAT,propertyTO.getLatitude());
                intent.putExtra(PropertyMapActivity.KEY.LNG,propertyTO.getLongitude());
                startActivity(intent);
                break;
            }
            case R.id.btnPropertyRented:
            {
                if(userTO.getId()!=propertyTO.getUserId())
                {
                    Toast.makeText(this, "You are not the property owner, so you cannot change it.", Toast.LENGTH_SHORT).show();
                    return;
                }
                propertyTO.setStatus("Rented");
                propertyDetailsPresenter.updatePropertyStatusRented(userTO,propertyTO);
                break;
            }
            case R.id.btnRequestAppointment:
            {
                if(propertyTO.isAppointmentRequested())
                {
                    Toast.makeText(this, "You already requested an appointment for this property.", Toast.LENGTH_SHORT).show();
                    return;
                }
                propertyDetailsPresenter.requestAppointment(userTO,propertyTO);
                break;
            }
        }
    }

    @Override
    public void setUserDetails(UserTO userTO) {
        this.userTO=userTO;
        propertyDetailsPresenter.getPropertyOwnerDetails(userTO,propertyTO);
    }

    @Override
    public void setOwnerPropertyData(UserTO ownerUser, PropertyTO propertyTO)
    {
        if(ownerUser==null || propertyTO==null)
        {
            return;
        }
        btnPropertyRented.setVisibility(userTO.getId()==propertyTO.getUserId()?View.VISIBLE:View.INVISIBLE);
        txtOwnerName.setText(ownerUser.getName());
        txtOwnerPhone.setText(ownerUser.getPhone());
        txtOwnerEmail.setText(ownerUser.getEmail());
        txtPropertySize.setText("Size: "+propertyTO.getSize());
        txtPropertyStatus.setText("Status: "+propertyTO.getStatus());
        txtPropertyPrice.setText("Price: "+propertyTO.getPrice());
        txtPropertyRegisterDate.setText("Register Date: "+new SimpleDateFormat("yyyy/MM/dd").format(new Date(propertyTO.getRegisterDate())));
        txtPropertyDesc.setText(propertyTO.getLongDescription());
        appImagePresenter.load(getApplicationContext(),ownerUser.getPhoto(),imgOwner);
        btnRequestAppointment.setEnabled(!propertyTO.isAppointmentRequested());
    }

    @Override
    public void updatePropertyData(boolean status,UserTO userTO, PropertyTO propertyTO) {
        setOwnerPropertyData(userTO,propertyTO);
        if(status)
        {
            Toast.makeText(this, "Property updated successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setNewAppointment(boolean status) {
        if(!status)
        {
            btnRequestAppointment.setEnabled(true);
            propertyTO.setAppointmentRequested(false);
            Toast.makeText(this, "There is a problem, please try again", Toast.LENGTH_SHORT).show();
            return;
        }
        btnRequestAppointment.setEnabled(false);
        propertyTO.setAppointmentRequested(true);
        Toast.makeText(this, "The appointment has requested successfully", Toast.LENGTH_SHORT).show();
    }
}
