package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new UserBL(this).fetchLoginAccountSP().getEmail();
    }
}
