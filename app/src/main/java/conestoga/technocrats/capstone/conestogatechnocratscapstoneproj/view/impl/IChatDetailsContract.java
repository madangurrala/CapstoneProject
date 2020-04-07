package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;


import java.util.List;
import java.util.Map;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

public interface IChatDetailsContract
{
    public void userValidationResult(UserTO userTO,UserTO peerUserTo);
    public void chatListResult(UserTO userOwnerTO, UserTO userPeerTO,List<MessageTO> messageTOList);
    public void sendMessageResult(boolean status,List<MessageTO> messageTOList);
    public void receiveMessageResult(boolean status,List<MessageTO> messageTOList);
}
