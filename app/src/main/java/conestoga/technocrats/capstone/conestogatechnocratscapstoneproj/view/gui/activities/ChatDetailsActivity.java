package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.ChatDetailsPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters.ChatDetailsRecycleAdapter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatDetailsContract;

public class ChatDetailsActivity extends AppCompatActivity implements IChatDetailsContract {
    private ChatDetailsPresenter chatDetailsPresenter;
    private ChatDetailsRecycleAdapter chatDetailsRecycleAdapter;
    private long peerUserId;

    @BindView(R.id.editMsg)
    public TextInputEditText editMsg;
    @BindView(R.id.imgSendMsg)
    public ImageView imgSendMsg;
    @BindView(R.id.recycleViewMessages)
    public RecyclerView recycleViewMessages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);
        ButterKnife.bind(this);
        peerUserId = getIntent().getLongExtra(UserTO.KEY.ID_KEY, 0);
        chatDetailsPresenter = new ChatDetailsPresenter(this, this);
        chatDetailsPresenter.validateUser(peerUserId);
    }

    @OnClick({R.id.imgSendMsg})
    public void onClick(View view)
    {
        String msg=editMsg.getText().toString();
        if(msg.isEmpty())
        {
            editMsg.setError("Please enter a message");
            return;
        }
        editMsg.setText("");
        chatDetailsPresenter.sendMessage(msg);
    }

    @Override
    public void userValidationResult(UserTO userTO, UserTO peerUserTO) {
        if (userTO == null || peerUserTO == null) {
            return;
        }
        chatDetailsPresenter.setUserTO(userTO, peerUserTO);
        chatDetailsPresenter.getChatList(userTO);
    }

    @Override
    public void chatListResult(UserTO userOwnerTO, UserTO userPeerTO, List<MessageTO> messageTOList)
    {
        if(messageTOList==null)
        {
            return;
        }
        chatDetailsRecycleAdapter = new ChatDetailsRecycleAdapter(getApplicationContext(), userOwnerTO, userPeerTO, messageTOList);
        recycleViewMessages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleViewMessages.setAdapter(chatDetailsRecycleAdapter);
        recycleViewMessages.scrollToPosition(messageTOList.size()-1);
    }

    @Override
    public void sendMessageResult(boolean status,List<MessageTO> messageTOList)
    {
        chatDetailsRecycleAdapter.setMessageTOS(messageTOList);
        recycleViewMessages.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void receiveMessageResult(boolean status,List<MessageTO> messageTOList) {
        chatDetailsRecycleAdapter.setMessageTOS(messageTOList);
        recycleViewMessages.getAdapter().notifyDataSetChanged();
    }
}
