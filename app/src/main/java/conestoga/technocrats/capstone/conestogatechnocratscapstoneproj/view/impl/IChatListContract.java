package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import java.util.List;
import java.util.Map;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public interface IChatListContract
{
    public void userValidationResult(UserTO userTO);
    public void chatListResult(Map<Long, List<MessageTO>> allUsersMessage);
}
