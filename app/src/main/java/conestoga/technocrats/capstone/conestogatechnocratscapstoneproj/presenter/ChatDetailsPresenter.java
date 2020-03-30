package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;

import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Message;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import java.util.ArrayList;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatDetailsContract;

public class ChatDetailsPresenter implements RoomListener
{
    private Context ctx=null;
    private IChatDetailsContract iChatDetailsContract;
    private Scaledrone scaledrone;

    private String chatChannelId;
    private String chatChannelSecurityKey;
    private String chatRoomName;

    public ChatDetailsPresenter(Context ctx,IChatDetailsContract iChatDetailsContract)
    {
        this.ctx=ctx;
        this.iChatDetailsContract=iChatDetailsContract;
        chatChannelId=ctx.getResources().getString(R.string.chat_channel_id);
        chatChannelSecurityKey=ctx.getResources().getString(R.string.chat_channel_security_key);
        chatRoomName="chatTestRoomName";
        scaledrone=new Scaledrone(chatChannelId);
        scaledrone.connect(new Listener() {
            @Override
            public void onOpen() {
                // Since the class itself already implement RoomListener we can pass it as a target
                scaledrone.subscribe(chatRoomName, ChatDetailsPresenter.this);
            }

            @Override
            public void onOpenFailure(Exception ex) {

            }

            @Override
            public void onFailure(Exception ex) {

            }

            @Override
            public void onClosed(String reason) {

            }
        });
    }


    public void loadMessages()
    {
        List<MessageTO> messageTOS=new ArrayList<>();
        for(int i=0;i<50;i++)
        {
            MessageTO messageTO=new MessageTO();
            messageTO.setId(i);
            messageTO.setSenderId((i%2==0)?1:2);
            messageTO.setReceiverId((i%2==0)?2:1);
            messageTO.setMessage((i%2==0)?"You Sent this message"+i:"Other person sent this message:"+i);
            messageTOS.add(messageTO);
        }
        iChatDetailsContract.updatedMessagesList(messageTOS);
    }

    // Successfully connected to Scaledrone room
    @Override
    public void onOpen(Room room) {

    }

    // Connecting to Scaledrone room failed
    @Override
    public void onOpenFailure(Room room, Exception ex) {

    }

    // Received a message from Scaledrone room
    @Override
    public void onMessage(Room room, Message message) {

    }
}
