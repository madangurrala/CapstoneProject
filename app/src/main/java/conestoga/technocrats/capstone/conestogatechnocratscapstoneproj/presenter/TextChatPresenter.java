package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.content.Context;

import com.sendbird.android.ApplicationUserListQuery;
import com.sendbird.android.BaseChannel;
import com.sendbird.android.BaseMessage;
import com.sendbird.android.OpenChannel;
import com.sendbird.android.OpenChannelListQuery;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;
import com.sendbird.android.UserListQuery;
import com.sendbird.android.UserMessage;

import java.util.ArrayList;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatDetailsContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatListContract;

public class TextChatPresenter
{
    private Context ctx=null;
    private IChatListContract iChatListContract;
    private IChatDetailsContract iChatDetailsContract;


    public OpenChannel openChannel;
    public static String CHANNEL_URL="sendbird_open_channel_617_caa9b1bc1367e58c6d7cbbac2431e4070044b2af";
    public static String CHANNEL_NAME="Open Channel";
    public static String UNIQUE_HANDLER_ID="UNIQUE_HANDLER_ID";
    public static final String USER1_ID="user1";
    public static final String USER2_ID="user2";

    public TextChatPresenter(Context ctx, IChatListContract iChatListContract,IChatDetailsContract iChatDetailsContract)
    {
       this.ctx=ctx;
       this.iChatListContract=iChatListContract;
       this.iChatDetailsContract=iChatDetailsContract;
    }

    private void connect()
    {
        String USER1_ID= "10";
        SendBird.connect(USER1_ID, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                boolean isConnected=true;
                if (e != null) {
                    isConnected=false;
                    //return;
                }
                if(iChatListContract!=null)
                {
                    iChatListContract.connectionDetails(isConnected);
                }
            }
        });
    }

    private void createChannel()
    {
        OpenChannel.createChannel(new OpenChannel.OpenChannelCreateHandler() {
            @Override
            public void onResult(OpenChannel openChannel, SendBirdException e) {
                boolean isCreated=true;
                if (e != null) {
                    isCreated=false;
                    //return;
                }
                if(iChatListContract!=null)
                {
                    iChatListContract.newChannelDetails(isCreated,openChannel);
                }
            }
        });
    }

    private void enterChannel(String channelUrl)
    {
        OpenChannel.getChannel(channelUrl, new OpenChannel.OpenChannelGetHandler() {
            @Override
            public void onResult(OpenChannel openChannel, SendBirdException e) {
                if (e != null) {
                    if(iChatListContract!=null)
                    {
                        iChatListContract.enterChannelDetails(false);
                    }
                    return;
                }
                TextChatPresenter.this.openChannel=openChannel;
                openChannel.enter(new OpenChannel.OpenChannelEnterHandler() {
                    @Override
                    public void onResult(SendBirdException e) {
                        boolean isEntered=true;
                        if (e != null) {
                            isEntered=false;
                            /*iChatListContract.enterChannelDetails(false);
                            return;*/
                        }
                        if(iChatListContract!=null)
                        {
                            iChatListContract.enterChannelDetails(isEntered);
                        }
                    }
                });
            }
        });
    }

    private void listAllChannels()
    {
        OpenChannelListQuery channelListQuery = OpenChannel.createOpenChannelListQuery();
        channelListQuery.next(new OpenChannelListQuery.OpenChannelListQueryResultHandler() {
            @Override
            public void onResult(List<OpenChannel> openChannels, SendBirdException e) {
                if (e != null) {
                    iChatListContract.getAllChannelsList(null);
                    return;
                }
                iChatListContract.getAllChannelsList(openChannels);
            }
        });
    }

    private void listChannelUsers()
    {
        ApplicationUserListQuery applicationUserListQuery = SendBird.createApplicationUserListQuery();
        //applicationUserListQuery.setUserIdsFilter(userIds);
        applicationUserListQuery.next(new UserListQuery.UserListQueryResultHandler() {
            @Override
            public void onResult(List<User> list, SendBirdException e) {
                if (e != null) {
                    iChatDetailsContract.getAllChannelUsersList(null);
                    return;
                }
                /*if (list.get(0).getConnectionStatus() == User.ConnectionStatus.ONLINE) {
                    // User.ConnectionStatus consists of NON_AVAILABLE, ONLINE, and OFFLINE.
                    System.out.println("--------->USER 2 is online");
                }
                else{
                    System.out.println("--------->USER 2 is offline");
                }*/
                iChatDetailsContract.getAllChannelUsersList(list);
            }
        });
    }

    private void exitChannel(String channelUrl)
    {
        OpenChannel.getChannel(channelUrl, new OpenChannel.OpenChannelGetHandler() {
            @Override
            public void onResult(OpenChannel openChannel, SendBirdException e) {
                if (e != null) {
                    iChatDetailsContract.existChannelDetails(false,openChannel);
                    return;
                }

                openChannel.exit(new OpenChannel.OpenChannelExitHandler() {
                    @Override
                    public void onResult(SendBirdException e) {
                        if (e != null) {
                            iChatDetailsContract.existChannelDetails(false,openChannel);
                            return;
                        }
                        iChatDetailsContract.existChannelDetails(true,openChannel);
                    }
                });
            }
        });
    }


    private void deleteChannel()
    {
        openChannel.delete(new OpenChannel.OpenChannelDeleteHandler() {
            @Override
            public void onResult(SendBirdException e) {
                if (e != null) {
                    iChatListContract.deleteChannelDetails(false,openChannel);
                    return;
                }
                iChatListContract.deleteChannelDetails(true,openChannel);
            }
        });
    }


    private void sendMessage(String message)
    {
        openChannel.sendUserMessage(message, new BaseChannel.SendUserMessageHandler() {
            @Override
            public void onSent(UserMessage userMessage, SendBirdException e) {
                if (e != null) {
                    iChatDetailsContract.sendMessageDetails(false,"","",message);
                    return;
                }
                iChatDetailsContract.sendMessageDetails(true,"","",message);
            }
        });
    }

    private void receiveMessages()
    {
        SendBird.addChannelHandler(UNIQUE_HANDLER_ID, new SendBird.ChannelHandler() {
            @Override
            public void onMessageReceived(BaseChannel channel, BaseMessage message) {
                iChatListContract.receiveMessageDetails(message.getSender().getUserId(),"",message.getMessage(),channel);
                iChatDetailsContract.receiveMessageDetails(message.getSender().getUserId(),"",message.getMessage(),channel);
            }
        });

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


    public void loadMessages() {
        List<MessageTO> messageTOS = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            MessageTO messageTO = new MessageTO();
            messageTO.setId(i);
            messageTO.setSenderId((i % 2 == 0) ? 1 : 2);
            messageTO.setReceiverId((i % 2 == 0) ? 2 : 1);
            messageTO.setMessage((i % 2 == 0) ? "You Sent this message" + i : "Other person sent this message:" + i);
            messageTOS.add(messageTO);
        }
        iChatDetailsContract.updatedMessagesList(messageTOS);
    }
}
