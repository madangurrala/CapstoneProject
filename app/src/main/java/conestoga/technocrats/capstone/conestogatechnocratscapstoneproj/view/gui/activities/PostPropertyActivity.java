package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.nio.charset.CharacterCodingException;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;

public class PostPropertyActivity extends AppCompatActivity {

    private MaterialButton cancelButton, submitButton, imageButton;
    private Intent cancelPostIntent;
    private Spinner propertyType;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property);

        cancelButton = findViewById(R.id.btnCancel);
        submitButton = findViewById(R.id.btnSubmit);
        imageButton = findViewById(R.id.btnImage);
        propertyType = findViewById(R.id.pType_spinner);
        
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

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        //Permission not granted
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);


                    }else{
                        //Permission granted
                        pickImageFromGallery();

                    }
                }else{
                    //OS is less than marshmallow
                    pickImageFromGallery();
                }

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
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    pickImageFromGallery();
                }else {
                    //Permission was denied
                    Toast.makeText(this, "Permission to upload the image was denied !",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && resultCode == IMAGE_PICK_CODE){

        }
    }*/
}
