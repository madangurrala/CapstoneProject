package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.SignUpAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.AskAccountActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.MainActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ISignUpAccountContract;

public class SignUpAccountFragment extends Fragment implements View.OnClickListener, ISignUpAccountContract {
    private SignUpAccountPresenter signUpAccountPresenter = null;
    private FrameLayout rootFrameLayout;
    @BindView(R.id.btnSignUp)
    public Button btnSignUp;
    @BindView(R.id.btnLogin)
    public Button btnLogin;
    @BindView(R.id.editEmail)
    public TextInputEditText editEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootFrameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_signup_account, container, false);
        ButterKnife.bind(this, rootFrameLayout);
        signUpAccountPresenter = new SignUpAccountPresenter(getActivity(),this);
        return rootFrameLayout;
    }

    @OnClick({R.id.btnSignUp,R.id.btnLogin})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp: {
                UserTO userTO = new UserTO();
                userTO.setEmail(editEmail.getText().toString());
                signUpAccountPresenter.validateUserData(userTO);
                break;
            }
            case R.id.btnLogin:
            {
                if(getActivity()!=null && getActivity() instanceof AskAccountActivity)
                {
                    AskAccountActivity askAccountActivity=(AskAccountActivity)getActivity();
                    askAccountActivity.getAskAccountPresenter().showRightFragment(askAccountActivity.getLoginFragment(),getResources().getString(R.string.login));
                }
                break;
            }
        }

    }

    @Override
    public void signUpStatus(boolean status, UserTO userTO) {
        if (status) {
            Toast.makeText(getActivity(), "Welcome!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        } else {
            Toast.makeText(getActivity(), "Try again!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void userDataValidationStatus(boolean status, UserTO userTO) {
        if (status) {
            Toast.makeText(getActivity(), "Good Job!", Toast.LENGTH_SHORT).show();
            signUpAccountPresenter.registerUser(userTO);
        } else {
            Toast.makeText(getActivity(), "Give right data!", Toast.LENGTH_SHORT).show();
        }
    }
}
