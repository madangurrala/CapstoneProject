package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.ProfilePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private ProfilePresenter profilePresenter=null;
    private FragmentManager fragmentManager=null;

    private FrameLayout displayFrameLayout;
    private View view;
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
        displayFrameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, displayFrameLayout);
        //profilePresenter = new ProfilePresenter(getContext(), this) ;
        emailText= view.findViewById(R.id.emailText);
        phoneText = view.findViewById(R.id.phoneText);
        fNameText = view.findViewById(R.id.fNameText);
        lNameText = view.findViewById(R.id.lNameText);
        btnEditProfile = view.findViewById(R.id.editProfile);
        btnUpdateProfile = view.findViewById(R.id.updateProfile);

        /*fNameText.setText(new UserBL(getContext()).fetchLoginAccountSP().getFamily());
        lNameText.setText(new UserBL(getContext()).fetchLoginAccountSP().getName());
        emailText.setText( new UserBL(getContext()).fetchLoginAccountSP().getEmail());
        phoneText.setText(new UserBL(getContext()).fetchLoginAccountSP().getPhone1());*/

        //Static data testing
        fNameText.setText("User Name");

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
        // Inflate the layout for this fragment
        return view;
    }

}
