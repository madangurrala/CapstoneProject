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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.root.AppImagePresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.ChatDetailsActivity;

public class MainChatRecycleAdapter extends RecyclerView.Adapter<MainChatRecycleAdapter.ViewHolder> {
    private Context ctx;
    private List<MessageTO> messageTOS;
    private AppImagePresenter appImagePresenter;

    public MainChatRecycleAdapter(Context ctx, List<MessageTO> messageTOS) {
        this.ctx = ctx;
        this.messageTOS = messageTOS;
        appImagePresenter=new AppImagePresenter();
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
        return messageTOS != null ? messageTOS.size() : 0;
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
        }

        public void setData(int position)
        {
            MessageTO messageTO=messageTOS.get(position);
            txtTitle.setText("Item Text "+position);
            txtSubTitle.setText("Item Subtitle "+position);
            appImagePresenter.load(ctx,messageTO.getMessageIcon(),imgItem);
        }

        @OnClick(R.id.rootConstraint)
        public void onClick(View view)
        {
            Intent intent=new Intent(ctx, ChatDetailsActivity.class);
            ctx.startActivity(intent);
        }
    }

}
