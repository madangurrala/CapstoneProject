package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatListContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IPropertiesContract;

public class ChatListPresenter
{
    private Context ctx=null;
    private IChatListContract iChatListContract;

    public ChatListPresenter(Context ctx, IChatListContract iChatListContract)
    {
       this.ctx=ctx;
       this.iChatListContract=iChatListContract;
    }

    public void getChatsList()
    {
        // TODO: 16/02/20 Update this part later
        List<MessageTO> messageTOS=new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            MessageTO messageTO=new MessageTO();
            if(i%2==0)
            {
                messageTO.setMessageIcon("https://cdn.iconscout.com/icon/free/png-512/avatar-367-456319.png");
            }
            else
            {
                messageTO.setMessageIcon("https://image.flaticon.com/icons/png/512/194/194938.png");
            }
            messageTOS.add(messageTO);
        }
        iChatListContract.fillChatListRecycleView(messageTOS);
    }
}
