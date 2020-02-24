package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;

public class SplashActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        showPermissionDialog();

       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,AskAccountActivity.class));
                SplashActivity.this.finish();
            }
        },300);*/
    }


    private void showPermissionDialog()
    {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton(getResources().getString(R.string.agree),(DialogInterface dialog,int which)->{

                })
                .setNegativeButton(getResources().getString(R.string.disagree),(DialogInterface dialog,int which)->{

                })
                .show();
    }

    @Override
    public void onBackPressed() {
    }
}
