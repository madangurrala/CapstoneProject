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

    @OnClick(value = {R.id.btnShowMap,R.id.btnRequestAppointment})
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
            case R.id.btnRequestAppointment:
            {
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
    public void setOwnerPropertyData(UserTO userTO, PropertyTO propertyTO)
    {
        if(userTO==null || propertyTO==null)
        {
            return;
        }
        txtOwnerName.setText(userTO.getName());
        txtOwnerPhone.setText(userTO.getPhone());
        txtOwnerEmail.setText(userTO.getEmail());
        txtPropertySize.setText(propertyTO.getSize());
        txtPropertyStatus.setText(propertyTO.getStatus());
        txtPropertyPrice.setText(propertyTO.getPrice());
        txtPropertyRegisterDate.setText(new SimpleDateFormat("yyyy/MM/dd hh:mm aa").format(new Date(propertyTO.getRegisterDate())));
        txtPropertyDesc.setText(propertyTO.getLongDescription());
        appImagePresenter.load(getApplicationContext(),userTO.getPhoto(),imgOwner);
    }

    @Override
    public void setNewAppointment(boolean status) {
        if(!status)
        {
            btnRequestAppointment.setEnabled(true);
            Toast.makeText(this, "There is a problem, please try again", Toast.LENGTH_SHORT).show();
            return;
        }
        btnRequestAppointment.setEnabled(false);
        Toast.makeText(this, "The appointment has requested successfully", Toast.LENGTH_SHORT).show();
    }
}
