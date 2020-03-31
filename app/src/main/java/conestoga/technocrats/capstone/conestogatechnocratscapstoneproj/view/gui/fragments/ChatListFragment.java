package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sendbird.android.BaseChannel;
import com.sendbird.android.OpenChannel;
import com.sendbird.android.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.TextChatPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters.MainChatRecycleAdapter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatListContract;

public class ChatListFragment extends Fragment implements IChatListContract {
    private TextChatPresenter chatListPresenter;

    private ConstraintLayout rootConstraint;
    @BindView(R.id.recycleView)
    public RecyclerView recycleView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootConstraint = (ConstraintLayout) inflater.inflate(R.layout.recycle_view_layout, container, false);
        ButterKnife.bind(this, rootConstraint);
        chatListPresenter=new TextChatPresenter(getActivity(),this,null);
        chatListPresenter.getChatsList();
        return rootConstraint;
    }


    @Override
    public void connectionDetails(boolean isConnected) {

    }

    @Override
    public void newChannelDetails(boolean isCreated, OpenChannel openChannel) {

    }

    @Override
    public void enterChannelDetails(boolean isEntered) {

    }

    @Override
    public void getAllChannelsList(List<OpenChannel> openChannelList) {

    }

    @Override
    public void deleteChannelDetails(boolean isDeleted, OpenChannel openChannel) {

    }

    @Override
    public void receiveMessageDetails(String senderId, String receiverId, String message, BaseChannel baseChannel) {

    }

    @Override
    public void fillChatListRecycleView(List<MessageTO> messageTOS) {
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recycleView.setAdapter(new MainChatRecycleAdapter(getActivity(),messageTOS));
    }
}

