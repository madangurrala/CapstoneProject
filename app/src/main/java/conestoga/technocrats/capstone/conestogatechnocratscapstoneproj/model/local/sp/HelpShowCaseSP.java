package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.sp;

import android.content.Context;
import android.content.SharedPreferences;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public class HelpShowCaseSP
{
    public static final String HELP_SHOW_CASE_PREFERENCES = "help_show_case_sp";
    public static final String IS_FIRST_LAUNCH_KEY = "is_first_launch_key";

    private Context ctx=null;
    private SharedPreferences sp;

    public HelpShowCaseSP(Context ctx)
    {
        this.ctx=ctx;
        sp=this.ctx.getSharedPreferences(HELP_SHOW_CASE_PREFERENCES,Context.MODE_PRIVATE);
    }

    public void updateFirstLaunch(boolean status)
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putBoolean(IS_FIRST_LAUNCH_KEY,status);
        editor.apply();
    }

    public boolean isFirstLaunch()
    {
        return sp.getBoolean(IS_FIRST_LAUNCH_KEY,true);
    }
}
