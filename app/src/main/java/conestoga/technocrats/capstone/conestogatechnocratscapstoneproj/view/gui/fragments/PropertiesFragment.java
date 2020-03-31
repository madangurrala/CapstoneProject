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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.MainPropertyPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters.MainPropertiesRecycleAdapter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IPropertiesContract;

public class PropertiesFragment extends Fragment implements IPropertiesContract {
    private MainPropertyPresenter mainPropertyPresenter;

    private ConstraintLayout rootConstraint;
    @BindView(R.id.recycleView)
    public RecyclerView recycleView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootConstraint = (ConstraintLayout) inflater.inflate(R.layout.recycle_view_layout, container, false);
        ButterKnife.bind(this, rootConstraint);
        mainPropertyPresenter=new MainPropertyPresenter(getActivity().getApplicationContext(),this);
        mainPropertyPresenter.getPropertiesList();
        return rootConstraint;
    }

    @Override
    public void fillPropertiesRecycleView(List<PropertyTO> propertyTOS) {
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recycleView.setAdapter(new MainPropertiesRecycleAdapter(getActivity(),propertyTOS));
    }

    @Override
    public void addProperty(boolean status) {

    }
}
