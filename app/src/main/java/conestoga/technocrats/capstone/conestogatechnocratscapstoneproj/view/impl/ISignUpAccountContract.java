package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public interface ISignUpAccountContract
{
    public void signUpStatus(boolean status,UserTO userTO);
    public void userDataValidationStatus(boolean status,UserTO userTO);

    public void signUpEmailInValid();
    public void signUpPasswordInValid();
}
