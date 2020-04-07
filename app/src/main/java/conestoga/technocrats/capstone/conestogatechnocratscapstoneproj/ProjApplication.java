package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj;

import android.app.Application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.utils.FirebaseUtil;

public class ProjApplication extends Application
{
    public static Map<Long, List<MessageTO>> allUsersMessage=new HashMap<>();
    public FirebaseUtil firebaseUtil;
    public UserTO userTO;

    /*public static final String APP_ID = "158B151C-7439-46B9-82CD-625D52D17D26";
    public static final String VERSION = "3.0.40";*/

    @Override
    public void onCreate() {
        super.onCreate();
        firebaseUtil = FirebaseUtil.getInstance(this);
        firebaseUtil.startConnection();
    }
}
