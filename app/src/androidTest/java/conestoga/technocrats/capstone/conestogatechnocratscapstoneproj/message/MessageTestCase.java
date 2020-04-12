package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.message;

import android.app.Activity;
import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.utils.FirebaseUtil;

@RunWith(AndroidJUnit4.class)
public class MessageTestCase {
    @Test
    public void getPropertyList() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MessageTO messageTO = new MessageTO();
        messageTO.setSenderId(11);
        messageTO.setReceiverId(33);
        messageTO.setMessage("Hello, I got your appointment request, so, let's start.....");
        messageTO.setRegisterDate(new Date().getTime());
        FirebaseUtil.getInstance(appContext).sendMessage(messageTO, new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Assert.assertNotNull(documentReference);
            }
        });
    }
}
