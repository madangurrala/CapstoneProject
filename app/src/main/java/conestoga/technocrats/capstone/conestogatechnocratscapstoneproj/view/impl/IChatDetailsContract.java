package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import com.sendbird.android.BaseChannel;
import com.sendbird.android.OpenChannel;
import com.sendbird.android.User;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;

public interface IChatDetailsContract
{
    public void getAllChannelUsersList(List<User> userList);

    public void existChannelDetails(boolean isExited, OpenChannel openChannel);

    public void sendMessageDetails(boolean isSent, String senderId, String receiverId, String message);

    public void receiveMessageDetails(String senderId, String receiverId, String message, BaseChannel baseChannel);

    public void updatedMessagesList(List<MessageTO> messageTOS);
}
