package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.root.AppImagePresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.ChatDetailsActivity;

public class MainChatRecycleAdapter extends RecyclerView.Adapter<MainChatRecycleAdapter.ViewHolder> {
    private Context ctx;
    private Map<Long, List<MessageTO>> allUsersMessage;
    private List<Long> messageUsersKeyList=new ArrayList<>();
    private AppImagePresenter appImagePresenter;

    public MainChatRecycleAdapter(Context ctx,Map<Long, List<MessageTO>> allUsersMessage) {
        this.ctx = ctx;
        this.allUsersMessage = allUsersMessage;
        appImagePresenter=new AppImagePresenter();
        messageUsersKeyList.addAll(allUsersMessage.keySet());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.recycle_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return messageUsersKeyList != null ? messageUsersKeyList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rootConstraint)
        public ConstraintLayout rootConstraint;
        @BindView(R.id.imgItem)
        public ImageView imgItem;
        @BindView(R.id.txtTitle)
        public TextView txtTitle;
        @BindView(R.id.txtSubTitle)
        public TextView txtSubTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imgItem.setImageResource(R.drawable.ic_perm_identity);
        }

        private void setData(int position)
        {
            long userId=messageUsersKeyList.get(position);
            rootConstraint.setTag(userId);
            MessageTO messageTO=allUsersMessage.get(userId).get(0);
            txtTitle.setText(messageTO.getMessage());
            txtSubTitle.setText(new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date(messageTO.getRegisterDate())));
            //appImagePresenter.load(ctx,messageTO.getMessageIcon(),imgItem);
        }

        @OnClick(R.id.rootConstraint)
        public void onClick(View view)
        {
            long userId=Integer.parseInt(view.getTag().toString());
            Intent intent=new Intent(ctx, ChatDetailsActivity.class);
            intent.putExtra(UserTO.KEY.ID_KEY,userId);
            ctx.startActivity(intent);
        }
    }

}
