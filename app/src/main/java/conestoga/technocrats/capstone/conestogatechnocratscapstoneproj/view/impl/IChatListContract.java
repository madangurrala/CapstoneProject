package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;

public interface IChatListContract
{
    public void fillChatListRecycleView(List<MessageTO> messageTOS);
}
