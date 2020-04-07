package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.utils;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

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

}
