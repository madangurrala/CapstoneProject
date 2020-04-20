package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.app.Activity;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.ProjApplication;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.utils.FirebaseUtil;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatListContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IMessageTask;

public class ChatListPresenter implements IMessageTask
{
    private Activity activity;
    private UserBL userBL;
    private IChatListContract iChatListContract;

    public ChatListPresenter(Activity activity,IChatListContract iChatListContract)
    {
        this.activity=activity;
        this.iChatListContract=iChatListContract;
        userBL=new UserBL(activity.getApplicationContext());
    }

    public void validateUser()
    {
        new AsyncTaskGetUserAction(userBL, iChatListContract).execute();
    }

    public void getChatList(UserTO userTO)
    {
        FirebaseUtil.getInstance(activity).startChatMessageListener(activity,userTO,this);
    }

    @Override
    public void messageListTask(UserTO userTO,List<MessageTO> messageTOList) {
        if(messageTOList==null)
        {
            return;
        }
        ProjApplication.allUsersMessage.clear();
        for(MessageTO messageTO:messageTOList)
        {
            long key=messageTO.getSenderId()!=userTO.getId()?messageTO.getSenderId():messageTO.getReceiverId();
            if(!ProjApplication.allUsersMessage.containsKey(key))
            {
                ProjApplication.allUsersMessage.put(key,new ArrayList<>());
            }
            ProjApplication.allUsersMessage.get(key).add(messageTO);
        }
        iChatListContract.chatListResult(ProjApplication.allUsersMessage);
    }


    private static class AsyncTaskGetUserAction extends AsyncTask<Void, Void, UserTO> {
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<IChatListContract> iChatListContractWeakReference;

        public AsyncTaskGetUserAction(UserBL userBL, IChatListContract iChatListContract) {
            userBLWeakReference = new WeakReference<>(userBL);
            iChatListContractWeakReference = new WeakReference<>(iChatListContract);
        }

        @Override
        protected UserTO doInBackground(Void... voids) {
            UserTO userTO = userBLWeakReference.get().fetchLoginAccountSP();
            if (userTO == null || userTO.getEmail() == null) {
                return null;
            }
            userTO = userBLWeakReference.get().fetchUser(userTO.getEmail());
            if (userTO == null || userTO.getToken() == null) {
                return null;
            }
            return userTO;
        }

        @Override
        protected void onPostExecute(UserTO userTO) {
            super.onPostExecute(userTO);
            iChatListContractWeakReference.get().userValidationResult(userTO);
        }
    }
}
