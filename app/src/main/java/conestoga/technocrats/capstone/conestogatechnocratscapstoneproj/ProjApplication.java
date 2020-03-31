package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj;

import android.app.Application;

import com.sendbird.android.SendBird;

public class ProjApplication extends Application
{
    public static final String APP_ID = "158B151C-7439-46B9-82CD-625D52D17D26";
    public static final String VERSION = "3.0.40";

    @Override
    public void onCreate() {
        super.onCreate();
        SendBird.init(APP_ID, getApplicationContext());
    }
}
