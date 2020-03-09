package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatDetailsContract;

public class ChatDetailsPresenter
{
    private Context ctx=null;
    private IChatDetailsContract iChatDetailsContract;

    public ChatDetailsPresenter(Context ctx,IChatDetailsContract iChatDetailsContract)
    {
        this.ctx=ctx;
        this.iChatDetailsContract=iChatDetailsContract;
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
}
