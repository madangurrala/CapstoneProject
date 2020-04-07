package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.root.AppImagePresenter;

public class ChatDetailsRecycleAdapter extends RecyclerView.Adapter<ChatDetailsRecycleAdapter.ViewHolder>
{
    private Context ctx;
    private AppImagePresenter appImagePresenter;
    private UserTO userOwnerTO;
    private UserTO userPeerTO;
    private List<MessageTO> messageTOS;
    private final int SENDER_TYPE=1;
    private final int RECEIVER_TYPE=2;

    public ChatDetailsRecycleAdapter(Context ctx,UserTO userOwnerTO,UserTO userPeerTO,List<MessageTO> messageTOS)
    {
        this.ctx=ctx;
        appImagePresenter=new AppImagePresenter();
        this.userOwnerTO=userOwnerTO;
        this.userPeerTO=userPeerTO;
        if(messageTOS!=null)
        {
            this.messageTOS=messageTOS;
        }
        else
        {
            this.messageTOS=new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;
        LayoutInflater inflater=LayoutInflater.from(ctx);
        if(viewType==SENDER_TYPE)
        {
            view= inflater.inflate(R.layout.chat_details_sender_item,parent,false);
        }
        else
        {
            view= inflater.inflate(R.layout.chat_details_receiver_item,parent,false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
        holder.setViewsListeners();
    }

    @Override
    public int getItemCount() {
        return messageTOS.size();
    }

    public void setMessageTOS(List<MessageTO> messageTOS)
    {
        this.messageTOS=messageTOS;
    }

    @Override
    public int getItemViewType(int position) {
        return messageTOS.get(position).getSenderId()==userOwnerTO.getId()?SENDER_TYPE:RECEIVER_TYPE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.rootConstraint)
        public ConstraintLayout rootConstraint;
        @BindView(R.id.imgAvatar)
        public ImageView imgAvatar;
        @BindView(R.id.txtMsg)
        public TextView txtMsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(int position)
        {
            MessageTO messageTO=messageTOS.get(position);
            if(getItemViewType()==SENDER_TYPE)
            {
                setSenderData(messageTO);
            }
            else
            {
                setReceiverData(messageTO);
            }
        }

        private void setSenderData(MessageTO messageTO)
        {

            appImagePresenter.loadCircle(ctx,userOwnerTO.getPhoto(),imgAvatar);
            txtMsg.setText(messageTO.getMessage());
        }

        private void setReceiverData(MessageTO messageTO)
        {
            appImagePresenter.loadCircle(ctx,userPeerTO.getPhoto(),imgAvatar);
            txtMsg.setText(messageTO.getMessage());
        }


        public void setViewsListeners()
        {

        }

    }

}
