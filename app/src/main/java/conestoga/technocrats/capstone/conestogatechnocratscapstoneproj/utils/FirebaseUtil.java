package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.utils;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IMessageTask;

public class FirebaseUtil {
    private final String KEY_MESSAGES_COLLECTION = "capstone_message_collection";

    private static FirebaseUtil firebaseUtil = null;
    private Context context;
    private FirebaseFirestore firestore;
    private CollectionReference collectionReference;

    private FirebaseUtil() {
    }

    public static FirebaseUtil getInstance(Context context) {
        if (firebaseUtil == null) {
            firebaseUtil = new FirebaseUtil();
            firebaseUtil.context = context;
        }
        return firebaseUtil;
    }

    public void startConnection() {
        firestore = FirebaseFirestore.getInstance();
        collectionReference = firestore.collection(KEY_MESSAGES_COLLECTION);
    }

    public void startChatMessageListener(Activity activity,UserTO userTO,IMessageTask iMessageTask)
    {
        if(userTO==null)
        {
            return;
        }
        collectionReference.addSnapshotListener(activity, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                List<MessageTO> messageTOList=new ArrayList<>();
                List<DocumentSnapshot> documentSnapshotList=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot:documentSnapshotList)
                {
                    MessageTO messageTO=snapshot.toObject(MessageTO.class);
                    if(messageTO.getSenderId()==userTO.getId() || messageTO.getReceiverId()==userTO.getId())
                    {
                        messageTOList.add(messageTO);
                    }
                }
                arrangeMessages(userTO,messageTOList,iMessageTask);
            }
        });
    }

    public void getChatList(Activity activity, UserTO userTO, OnSuccessListener<List<QuerySnapshot>> successListener) {
        Task<QuerySnapshot> task1=collectionReference
                .whereEqualTo(MessageTO.KEY.SENDER_ID_KEY, userTO.getId())
                .orderBy(MessageTO.KEY.REGISTER_DATE_KEY, Query.Direction.DESCENDING)
                .get();

        Task<QuerySnapshot> task2=collectionReference
                .whereEqualTo(MessageTO.KEY.RECEIVER_ID_KEY, userTO.getId())
                .orderBy(MessageTO.KEY.REGISTER_DATE_KEY, Query.Direction.DESCENDING)
                .get();

        Task<List<QuerySnapshot>> querySnapshotTasks=Tasks.whenAllSuccess(task1,task2);
        querySnapshotTasks.addOnSuccessListener(activity,successListener);
    }

    public void sendMessage(Activity activity, MessageTO messageTO, OnSuccessListener<DocumentReference> successListener) {
        collectionReference.add(messageTO).addOnSuccessListener(activity, successListener);
    }

    public void sendMessage(MessageTO messageTO, OnSuccessListener<DocumentReference> successListener) {
        collectionReference.add(messageTO).addOnSuccessListener(successListener);
    }


    private void arrangeMessages(UserTO userTO,List<MessageTO> messageTOList, IMessageTask iMessageTask)
    {
        if(messageTOList!=null && messageTOList.size()>0)
        {
            Collections.sort(messageTOList, new Comparator<MessageTO>() {
                @Override
                public int compare(MessageTO msg1, MessageTO msg2)
                {
                    if(msg1.getRegisterDate()>msg2.getRegisterDate())
                    {
                        return 1;
                    }
                    else if(msg1.getRegisterDate()<msg2.getRegisterDate())
                    {
                        return -1;
                    }
                    return 0;
                }
            });
        }
        if(iMessageTask!=null)
        {
            iMessageTask.messageListTask(userTO,messageTOList);
        }
    }

}
