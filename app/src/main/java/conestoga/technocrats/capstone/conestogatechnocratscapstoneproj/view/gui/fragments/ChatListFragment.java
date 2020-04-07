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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.ChatListPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters.MainChatRecycleAdapter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IChatListContract;

public class ChatListFragment extends Fragment implements IChatListContract, SwipeRefreshLayout.OnRefreshListener {

    private ChatListPresenter chatListPresenter;

    private LinearLayoutManager linearLayoutManager;
    private MainChatRecycleAdapter adapter;

    private ConstraintLayout rootConstraint;
    @BindView(R.id.swipeRefreshLayout)
    public SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycleView)
    public RecyclerView recycleView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootConstraint = (ConstraintLayout) inflater.inflate(R.layout.recycle_view_layout, container, false);
        ButterKnife.bind(this, rootConstraint);
        initChatListRecycle();
        chatListPresenter=new ChatListPresenter(getActivity(),this);
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
        return rootConstraint;
    }

    private void initChatListRecycle()
    {
        linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recycleView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void userValidationResult(UserTO userTO) {
        if(userTO==null)
        {
            return;
        }
        chatListPresenter.getChatList(userTO);
    }

    @Override
    public void chatListResult(Map<Long, List<MessageTO>> allUsersMessage) {
        adapter=new MainChatRecycleAdapter(getActivity(),allUsersMessage);
        recycleView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        chatListPresenter.validateUser();
        swipeRefreshLayout.setRefreshing(false);
    }
}

