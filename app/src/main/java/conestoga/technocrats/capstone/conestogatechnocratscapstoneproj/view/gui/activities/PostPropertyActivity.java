package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.nio.charset.CharacterCodingException;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.MainPropertyPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IPropertiesContract;

public class PostPropertyActivity extends AppCompatActivity implements IPropertiesContract {

    private MainPropertyPresenter mainPropertyPresenter;

    @BindView(R.id.editTitle)
    public TextInputEditText editTitle;
    @BindView(R.id.editRent)
    public TextInputEditText editRent;
    @BindView(R.id.editSize)
    public TextInputEditText editSize;
    @BindView(R.id.editDuration)
    public TextInputEditText editDuration;
    @BindView(R.id.editBedroomCount)
    public TextInputEditText editBedroomCount;
    @BindView(R.id.editBathroomCount)
    public TextInputEditText editBathroomCount;
    @BindView(R.id.editAddress)
    public TextInputEditText editAddress;
    @BindView(R.id.hydroId)
    public CheckBox hydroId;
    @BindView(R.id.gasId)
    public CheckBox gasId;
    @BindView(R.id.internetId)
    public CheckBox internetId;
    @BindView(R.id.gymId)
    public CheckBox gymId;
    @BindView(R.id.furnishedId)
    public CheckBox furnishedId;
    @BindView(R.id.laundryId)
    public CheckBox laundryId;
    @BindView(R.id.btnUpdateLocation)
    public MaterialButton btnUpdateLocation;
    @BindView(R.id.txtLatitude)
    public TextView txtLatitude;
    @BindView(R.id.txtLongitude)
    public TextView txtLongitude;

    private MaterialButton cancelButton, submitButton;
    private Intent cancelPostIntent;
    private Spinner propertyType;


    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    private double latitude;
    private double longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property);
        ButterKnife.bind(this);

        cancelButton = findViewById(R.id.btnCancel);
        submitButton = findViewById(R.id.btnSubmit);
        propertyType = findViewById(R.id.pType_spinner);

        mainPropertyPresenter=new MainPropertyPresenter(this,this);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.proprtyType_array,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propertyType.setAdapter(adapter);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MaterialAlertDialogBuilder cancelAlert = new MaterialAlertDialogBuilder(PostPropertyActivity.this);

                cancelAlert.setTitle("Are you sure you want to Cancel?");
                cancelAlert.setMessage("Upon clicking on 'Okay', this post will be discarded and you will be taken to home page." +
                        "\n\nPlease select 'Dismiss' to continue posting. ");
                cancelAlert.setIcon(R.drawable.ic_alert);
                cancelAlert.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg, null));
                cancelAlert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        cancelPostIntent = new Intent(PostPropertyActivity.this, MainActivity.class);
                        startActivity(cancelPostIntent);

                    }
                });
                cancelAlert.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                cancelAlert.show();
            }
        });

        /*btnUpdateLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }else{
                        pickImageFromGallery();

                    }
                }else{
                    pickImageFromGallery();
                }

            }
        });*/


        btnUpdateLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (ActivityCompat.checkSelfPermission(PostPropertyActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(PostPropertyActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }else{
                        mainPropertyPresenter.isGpsServiceAvailable();

                    }
                }else{
                    mainPropertyPresenter.isGpsServiceAvailable();
                }

            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shortDesc= String.format(getResources().getString(R.string.property_short_description),
                        editBedroomCount.getText().toString(),
                        editBathroomCount.getText().toString(),
                        editDuration.getText().toString());

                String longDesc=String.format(getResources().getString(R.string.property_long_description),
                        editBedroomCount.getText().toString(),
                        editBathroomCount.getText().toString(),
                        editDuration.getText().toString(),
                        hydroId.isChecked(),
                        internetId.isChecked(),
                        gasId.isChecked(),
                        furnishedId.isChecked(),
                        laundryId.isChecked(),
                        gymId.isChecked());
                PropertyTO propertyTO=new PropertyTO();
                propertyTO.setTitle(editTitle.getText().toString());
                propertyTO.setSmallImagePath("Small Image Path");
                propertyTO.setBigImagePath("Big Image Path");
                propertyTO.setShortDescription(shortDesc);
                propertyTO.setLongDescription(longDesc);
                propertyTO.setStatus("Renting");
                propertyTO.setLatitude(latitude);
                propertyTO.setLongitude(longitude);
                propertyTO.setAddress(editAddress.getText().toString());
                propertyTO.setSize(editSize.getText().toString());
                propertyTO.setPrice(editRent.getText().toString());
                mainPropertyPresenter.addPropertyValidation(propertyTO);
            }
        });

    }

    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(permissions.length==grantResults.length && (grantResults[0] | grantResults[1]) == PackageManager.PERMISSION_GRANTED){
                    mainPropertyPresenter.isGpsServiceAvailable();
                }else {
                    Toast.makeText(PostPropertyActivity.this, "Application needs permission to have access to the location", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void isGpsServiceAvailable(boolean status) {
        if(!status)
        {
            return;
        }
        mainPropertyPresenter.updateDeviceCurrentLocation();
    }

    @Override
    public void updateProperLocation(double lat, double lng) {
        this.latitude=lat;
        this.longitude=lng;
        txtLatitude.setText(String.format("%s: %f",getResources().getString(R.string.latitude),lat));
        txtLongitude.setText(String.format("%s: %f",getResources().getString(R.string.longitude),lng));
    }

    @Override
    public void fillPropertiesRecycleView(List<PropertyTO> propertyTOS) {

    }

    @Override
    public void addProperty(boolean status)
    {
        if(status)
        {
            Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
            PostPropertyActivity.this.finish();
            return;
        }
        Toast.makeText(this, "Sorry, there is a problem, please try again later", Toast.LENGTH_SHORT).show();

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && resultCode == IMAGE_PICK_CODE){

        }
    }*/
}
