package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.AppointmentListPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters.MainAppointmentRecycleAdapter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IAppointmentsContract;

public class AppointmentsFragment extends Fragment implements IAppointmentsContract, SwipeRefreshLayout.OnRefreshListener {
    private AppointmentListPresenter appointmentListPresenter;

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
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        swipeRefreshLayout.setOnRefreshListener(this);
        appointmentListPresenter = new AppointmentListPresenter(getActivity(), this);
        requestAppointmentsList();
        return rootConstraint;
    }

    private void requestAppointmentsList()
    {
        appointmentListPresenter.getAppointmentsList();
    }

    @Override
    public void acceptAppointmentRequestResult(boolean status)
    {
        if(recycleView.getAdapter()!=null)
        {
            recycleView.getAdapter().notifyDataSetChanged();
        }
        if(status)
        {
            Toast.makeText(getActivity(), "Message sent successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getActivity(), "Message failed to send", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void fillAppointmentsRecycleView(UserTO userTO,List<AppointmentTO> appointmentTOS)
    {
        appointmentListPresenter.setUserTO(userTO);
        recycleView.setAdapter(new MainAppointmentRecycleAdapter(getActivity(),appointmentTOS,appointmentListPresenter));
    }

    @Override
    public void onRefresh() {
        requestAppointmentsList();
        swipeRefreshLayout.setRefreshing(false);
    }
}


