package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.SignUpAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.MainActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ISignUpAccountContract;

public class SignUpAccountFragment extends Fragment implements View.OnClickListener,ISignUpAccountContract
{
    private SignUpAccountPresenter signUpAccountPresenter=null;
    private ConstraintLayout rootConstraint;
    @BindView(R.id.btnSignUp)
    public Button btnSignUp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootConstraint= (ConstraintLayout) inflater.inflate(R.layout.fragment_signup_account,container,false);
        ButterKnife.bind(this,rootConstraint);
        signUpAccountPresenter=new SignUpAccountPresenter(this);
        return rootConstraint;
    }

    @OnClick({R.id.btnSignUp})
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnSignUp:
            {
                UserTO userTO=new UserTO();
                signUpAccountPresenter.validateUserData(userTO);
                break;
            }
        }

    }

    @Override
    public void signUpStatus(boolean status,UserTO userTO) {
        if(status)
        {
            Toast.makeText(getActivity(), "Welcome!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }
        else
        {
            Toast.makeText(getActivity(), "Try again!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void userDataValidationStatus(boolean status,UserTO userTO) {
        if(status)
        {
            Toast.makeText(getActivity(), "Good Job!", Toast.LENGTH_SHORT).show();
            signUpAccountPresenter.registerUser(userTO);
        }
        else
        {
            Toast.makeText(getActivity(), "Give right data!", Toast.LENGTH_SHORT).show();
        }
    }
}
