package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters;

import android.content.Context;
import android.graphics.Color;
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
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.root.AppImagePresenter;

public class MainAppointmentRecycleAdapter extends RecyclerView.Adapter<MainAppointmentRecycleAdapter.ViewHolder> {
    private Context ctx;
    private List<AppointmentTO> appointmentTOS;
    private AppImagePresenter appImagePresenter;

    public MainAppointmentRecycleAdapter(Context ctx, List<AppointmentTO> appointmentTOS) {
        this.ctx = ctx;
        this.appointmentTOS = appointmentTOS;
        appImagePresenter=new AppImagePresenter();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.recycle_item1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return appointmentTOS != null ? appointmentTOS.size() : 0;
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

        public void setData(int position) {
            AppointmentTO appointmentTO=appointmentTOS.get(position);
            txtTitle.setText("Item Text "+position);
            txtSubTitle.setText("Item Subtitle "+position);
            appImagePresenter.load(ctx,appointmentTO.getAppointmentIcon(),imgItem);
        }

        @OnClick(R.id.rootConstraint)
        public void onClick(View view)
        {
            Toast.makeText(ctx, "We still are working to complete this part", Toast.LENGTH_SHORT).show();
        }
    }

}
