package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.AppointmentListPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.root.AppImagePresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.utils.FirebaseUtil;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IAppointmentsContract;

public class MainAppointmentRecycleAdapter extends RecyclerView.Adapter<MainAppointmentRecycleAdapter.ViewHolder> {
    private Activity activity;
    private List<AppointmentTO> appointmentTOS;
    private AppointmentListPresenter appointmentListPresenter;

    public MainAppointmentRecycleAdapter(Activity activity, List<AppointmentTO> appointmentTOS,AppointmentListPresenter appointmentListPresenter) {
        this.activity = activity;
        this.appointmentTOS = appointmentTOS;
        this.appointmentListPresenter = appointmentListPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recycle_item1, parent, false);
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
        @BindView(R.id.imgBtn2)
        public ImageButton imgBtn2;
        @BindView(R.id.imgBtn3)
        public ImageButton imgBtn3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imgBtn2.setImageResource(R.drawable.ic_highlight_off);
            imgBtn2.setVisibility(View.INVISIBLE);
            imgBtn3.setImageResource(R.drawable.ic_done);
        }

        public void setData(int position) {
            AppointmentTO appointmentTO=appointmentTOS.get(position);
            txtTitle.setText(appointmentTO.getPeerTitle());
            txtSubTitle.setText(new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date(appointmentTO.getAppointmentDate())));
            imgItem.setImageResource(R.drawable.ic_business_center);
            if(appointmentTO.getStatus().equals(AppointmentTO.EAppointmentStatus.Accepted.getValue()))
            {
                imgBtn3.setImageResource(R.drawable.ic_done_all);
                imgBtn3.setEnabled(false);
            }
            else
            {
                imgBtn3.setImageResource(R.drawable.ic_done);
                imgBtn3.setEnabled(true);
            }
            imgBtn3.setTag(position);
        }

        @OnClick({R.id.imgBtn3})
        public void onClick(View view)
        {
            int position = Integer.parseInt(view.getTag().toString());
            AppointmentTO appointmentTO=appointmentTOS.get(position);
            switch (view.getId())
            {
                case R.id.imgBtn3:
                {
                    appointmentListPresenter.acceptAppointmentRequest(activity,appointmentTO);
                    break;
                }
            }
        }
    }

}
