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
            messageTOS.add(messageTO);
        }
        iChatListContract.fillChatListRecycleView(messageTOS);
    }
}
