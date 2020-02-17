package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IDisplayProfileContract;

public class DisplayProfilePresenter {

    private IDisplayProfileContract iDisplayProfileContract =null;
    public DisplayProfilePresenter(IDisplayProfileContract iDisplayProfileContract)
    {
        this.iDisplayProfileContract = iDisplayProfileContract;
    }

    /*public void loginUser(UserTO userTO)
    {
        iDisplayProfileContract.userLoginStatus(true,userTO);
    }*/
}
