package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.ProjApplication;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.remote.server.UserServerApi;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.utils.FirebaseUtil;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatDetailsContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatListContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IMessageTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatDetailsPresenter implements IMessageTask
{
    private Activity activity;
    private UserTO userTO;
    private UserTO peerUserTO;
    private UserBL userBL;
    private IChatDetailsContract iChatDetailsContract;

    public ChatDetailsPresenter(Activity activity, IChatDetailsContract iChatDetailsContract)
    {
        this.activity=activity;
        this.iChatDetailsContract=iChatDetailsContract;
        userBL=new UserBL(activity.getApplicationContext());
    }

    public void validateUser(long peerUserId)
    {
        new AsyncTaskGetUserAction(peerUserId,userBL, iChatDetailsContract).execute();
    }
    public void setUserTO(UserTO userTO,UserTO peerUserTO)
    {
        this.userTO=userTO;
        this.peerUserTO=peerUserTO;
    }
    public void getChatList(UserTO userTO)
    {
        FirebaseUtil.getInstance(activity).startChatMessageListener(activity,userTO,this);
    }

    public void sendMessage(String msg)
    {
        MessageTO messageTO=new MessageTO();
        messageTO.setSenderId((int) userTO.getId());
        messageTO.setReceiverId((int) peerUserTO.getId());
        messageTO.setMessage(msg);
        messageTO.setRegisterDate(new Date().getTime());
        FirebaseUtil.getInstance(activity.getApplicationContext()).sendMessage(activity, messageTO, new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference)
            {
                //ProjApplication.allUsersMessage.get(peerUserTO.getId()).add(messageTO);
                //iChatDetailsContract.sendMessageResult(true,ProjApplication.allUsersMessage.get(peerUserTO.getId()));
            }
        });
    }


    @Override
    public void messageListTask(UserTO userTO,List<MessageTO> messageTOList) {
        if(messageTOList==null)
        {
            return;
        }
        for(MessageTO messageTO:messageTOList)
        {
            long key=messageTO.getSenderId()!=userTO.getId()?messageTO.getSenderId():messageTO.getReceiverId();
            if(!ProjApplication.allUsersMessage.containsKey(key))
            {
                ProjApplication.allUsersMessage.put(key,new ArrayList<>());
            }
            ProjApplication.allUsersMessage.get(key).add(messageTO);
        }
        iChatDetailsContract.chatListResult(userTO,peerUserTO,ProjApplication.allUsersMessage.get(peerUserTO.getId()));
    }


    private static class AsyncTaskGetUserAction extends AsyncTask<Void, Void, UserTO> {
        private long peerUserId;
        private WeakReference<UserBL> userBLWeakReference;
        private WeakReference<IChatDetailsContract> iChatDetailsContractWeakReference;

        public AsyncTaskGetUserAction(long peerUserId,UserBL userBL, IChatDetailsContract iChatDetailsContract) {
            this.peerUserId = peerUserId;
            userBLWeakReference = new WeakReference<>(userBL);
            iChatDetailsContractWeakReference = new WeakReference<>(iChatDetailsContract);
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
            if(userTO==null)
            {
                iChatDetailsContractWeakReference.get().userValidationResult(null,null);
                return;
            }
            UserServerApi userServerApi=new UserServerApi(userTO.getToken());
            userServerApi.getUserById(peerUserId, new Callback<UserTO>() {
                @Override
                public void onResponse(Call<UserTO> call, Response<UserTO> response) {
                    if(response.code()!=200)
                    {
                        iChatDetailsContractWeakReference.get().userValidationResult(userTO,null);
                        return;
                    }
                    iChatDetailsContractWeakReference.get().userValidationResult(userTO,response.body());
                }

                @Override
                public void onFailure(Call<UserTO> call, Throwable t) {
                    iChatDetailsContractWeakReference.get().userValidationResult(null,null);
                }
            });
        }
    }
}
