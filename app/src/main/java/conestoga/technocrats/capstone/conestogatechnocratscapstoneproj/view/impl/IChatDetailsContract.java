package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;

public interface IChatDetailsContract
{
    public void updatedMessagesList(List<MessageTO> messageTOS);
}
