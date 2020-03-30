package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl;

import android.content.Context;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.root.RootBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da.UserDA;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.sp.LoginAccountSP;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public class UserBL extends RootBL
{
    public UserBL(Context ctx) {
        super(ctx);
    }

    public void registerUser(UserTO userTO)
    {
        UserDA userDA=db.userDA();
        userDA.insert(userTO);
    }
    public UserTO fetchUser(String email)
    {
        UserDA userDA=db.userDA();
        return userDA.select(email);
    }
    public boolean registerLoginAccountSP(UserTO userTO)
    {
        return new LoginAccountSP(ctx).insertLoginAccount(userTO);
    }
    public UserTO fetchLoginAccountSP()
    {
        return new LoginAccountSP(ctx).selectLoginAccount();
    }
}
