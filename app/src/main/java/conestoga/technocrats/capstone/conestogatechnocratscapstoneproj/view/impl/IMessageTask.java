package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public interface IMessageTask
{
    public void messageListTask(UserTO userTO,List<MessageTO> messageTOList);
}
