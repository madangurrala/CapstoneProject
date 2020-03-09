package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.ChatDetailsPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters.ChatDetailsRecycleAdapter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatDetailsContract;

public class ChatDetailsActivity extends AppCompatActivity implements IChatDetailsContract {
    private ChatDetailsPresenter chatDetailsPresenter;
    private ChatDetailsRecycleAdapter chatDetailsRecycleAdapter;
    private UserTO userOwnerTO;
    private UserTO userPeerTO;
    private MutableLiveData<List<MessageTO>> messageTOS;

    @BindView(R.id.editMsg)
    public TextInputEditText editMsg;
    @BindView(R.id.viewSendMsg)
    public ImageView viewSendMsg;
    @BindView(R.id.recycleViewMessages)
    public RecyclerView recycleViewMessages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);
        ButterKnife.bind(this);
        chatDetailsPresenter=new ChatDetailsPresenter(this,this);
        loadUsers();
        chatDetailsPresenter.loadMessages();

    }

    @Override
    public void updatedMessagesList(List<MessageTO> messageTOS) {
        buildRecycleViewMessages(userOwnerTO,userPeerTO,messageTOS);
    }

    private void loadUsers()
    {
        userOwnerTO=new UserTO();
        userOwnerTO.setId(1);
        userOwnerTO.setPhoto("https://cdn.iconscout.com/icon/free/png-512/avatar-367-456319.png");

        userPeerTO=new UserTO();
        userPeerTO.setId(2);
        userPeerTO.setPhoto("https://image.flaticon.com/icons/png/512/194/194938.png");
    }

    private void buildRecycleViewMessages(UserTO userOwnerTO,UserTO userPeerTO,List<MessageTO> messageTOS) {
        chatDetailsRecycleAdapter = new ChatDetailsRecycleAdapter(getApplicationContext(),userOwnerTO,userPeerTO,messageTOS);
        recycleViewMessages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycleViewMessages.setAdapter(chatDetailsRecycleAdapter);
    }
}
