package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl;

import com.sendbird.android.BaseChannel;
import com.sendbird.android.OpenChannel;
import com.sendbird.android.User;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;

public interface IChatListContract {
    public void connectionDetails(boolean isConnected);

    public void newChannelDetails(boolean isCreated, OpenChannel openChannel);

    public void enterChannelDetails(boolean isEntered);

    public void getAllChannelsList(List<OpenChannel> openChannelList);

    public void deleteChannelDetails(boolean isDeleted, OpenChannel openChannel);

    public void receiveMessageDetails(String senderId, String receiverId, String message, BaseChannel baseChannel);


    public void fillChatListRecycleView(List<MessageTO> messageTOS);
}
