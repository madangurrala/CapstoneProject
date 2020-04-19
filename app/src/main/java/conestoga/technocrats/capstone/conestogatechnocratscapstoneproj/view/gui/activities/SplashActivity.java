package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.imgSplash)
    public ImageView imgSplash;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        checkRequirePermissions();
    }

    private void startImgSplashAnim()
    {
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.splash_icon_bounce);
        imgSplash.startAnimation(animation);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int permissionStatus : grantResults) {
            if (permissionStatus == PackageManager.PERMISSION_DENIED) {
                SplashActivity.this.finish();
                return;
            }
        }
        goNextActivity();
    }

    private void checkRequirePermissions() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA

        };
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                showPermissionDialog(permissions);
                return;
            }
        }
        goNextActivity();
    }

    private void showPermissionDialog(String[] permissions) {
        new MaterialAlertDialogBuilder(this)
                .setTitle(getResources().getString(R.string.permission_title))
                .setMessage(getResources().getString(R.string.permission_msg))
                .setPositiveButton(getResources().getString(R.string.agree), (DialogInterface dialog, int which) -> {
                    ActivityCompat.requestPermissions(SplashActivity.this, permissions, 0);
                })
                .setNegativeButton(getResources().getString(R.string.disagree), (DialogInterface dialog, int which) -> {
                    SplashActivity.this.finish();
                })
                .show();
    }

    private void goNextActivity()
    {
        startImgSplashAnim();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, AskAccountActivity.class));
                SplashActivity.this.finish();
            }
        }, 5000);
    }

    @Override
    public void onBackPressed() {
    }
}
