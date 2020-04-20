package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public interface ILoginContract
{
    public void isUserDataValid(boolean status,UserTO userTO);
    public void userLoginStatus(boolean status, UserTO userTO);

    public void loginEmailNotValid();
    public void loginPassNotValid();
}
