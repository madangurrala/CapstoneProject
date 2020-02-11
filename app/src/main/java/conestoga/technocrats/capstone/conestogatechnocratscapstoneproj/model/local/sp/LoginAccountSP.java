package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.sp;

import android.content.Context;
import android.content.SharedPreferences;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public class LoginAccountSP
{
    public static final String LOGIN_ACCOUNT_PREFERENCES = "login_account";
    public static final String EMAIL_KEY = "email_key";

    private Context ctx=null;
    private SharedPreferences sp;

    public LoginAccountSP(Context ctx)
    {
        this.ctx=ctx;
        sp=this.ctx.getSharedPreferences(LOGIN_ACCOUNT_PREFERENCES,Context.MODE_PRIVATE);
    }

    public boolean insertLoginAccount(UserTO userTO)
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(EMAIL_KEY, userTO.getEmail());
        return editor.commit();
    }

    public UserTO selectLoginAccount()
    {
        if(!sp.contains(EMAIL_KEY))
        {
            return null;
        }
        UserTO userTO=new UserTO();
        userTO.setEmail(sp.getString(EMAIL_KEY,null));
        return userTO;
    }
}
