package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.ProfilePresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IProfileContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment  implements IProfileContract, SwipeRefreshLayout.OnRefreshListener {

    private ProfilePresenter profilePresenter=null;

    private View view;
    @BindView(R.id.swipeRefreshLayout)
    public SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.editProfile)
    public MaterialButton btnEditProfile;
    public TextInputEditText emailText;
    public TextInputEditText phoneText;
    public TextInputEditText fNameText;
    public TextInputEditText lNameText;
    public MaterialButton btnUpdateProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        //profilePresenter = new ProfilePresenter(getContext(), this) ;
        emailText= view.findViewById(R.id.emailText);
        phoneText = view.findViewById(R.id.phoneText);
        fNameText = view.findViewById(R.id.fNameText);
        lNameText = view.findViewById(R.id.lNameText);
        btnEditProfile = view.findViewById(R.id.editProfile);
        btnUpdateProfile = view.findViewById(R.id.updateProfile);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailText.setEnabled(true);
                fNameText.setEnabled(true);
                lNameText.setEnabled(true);
                phoneText.setEnabled(true);
                btnEditProfile.setEnabled(false);
                btnUpdateProfile.setEnabled(true);


                Toast.makeText(getActivity(), "Your profile can be updated now", Toast.LENGTH_LONG).show();

            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailText.setEnabled(false);
                fNameText.setEnabled(false);
                lNameText.setEnabled(false);
                phoneText.setEnabled(false);
                btnEditProfile.setEnabled(true);
                btnUpdateProfile.setEnabled(false);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(this);
        profilePresenter =new ProfilePresenter(getActivity().getApplicationContext(), this);
        requestUserProfileData();
        return view;
    }

    private void requestUserProfileData()
    {
        profilePresenter.getUserProfile();
    }


    @Override
    public void setUserProfileData(UserTO userTO)
    {
        if(userTO==null)
        {
            return;
        }
        fNameText.setText(userTO.getName());
        lNameText.setText(userTO.getFamily());
        emailText.setText(userTO.getEmail());
        phoneText.setText(userTO.getPhone());
    }

    @Override
    public void onRefresh() {
        requestUserProfileData();
        swipeRefreshLayout.setRefreshing(false);
    }
}
