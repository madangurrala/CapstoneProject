package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.LoginAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.AskAccountActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.MainActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ILoginContract;

public class AppointmentsFragment extends Fragment implements View.OnClickListener, ILoginContract {

    private LoginAccountPresenter loginAccountPresenter=null;

    private FrameLayout rootFrameLayout;
    @BindView(R.id.btnLogin)
    public MaterialButton btnLogin;
    @BindView(R.id.btnSignUp)
    public MaterialButton btnSignUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootFrameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, rootFrameLayout);
        loginAccountPresenter=new LoginAccountPresenter(this);
        return rootFrameLayout;
    }


    @OnClick({R.id.btnLogin,R.id.btnSignUp})
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnLogin:
            {
                UserTO userTO=new UserTO();
                loginAccountPresenter.validateUserData(userTO);
                break;
            }
            case R.id.btnSignUp:
            {
                if(getActivity()!=null && getActivity() instanceof AskAccountActivity)
                {
                    AskAccountActivity askAccountActivity=(AskAccountActivity)getActivity();
                    askAccountActivity.getAskAccountPresenter().showRightFragment(askAccountActivity.getSignUpAccountFragment(),getResources().getString(R.string.sign_up));
                }
                break;
            }
        }
    }

    @Override
    public void isUserDataValid(boolean status, UserTO userTO) {
        if(status)
        {
            loginAccountPresenter.loginUser(userTO);
        }
    }

    @Override
    public void userLoginStatus(boolean status, UserTO userTO) {
        Toast.makeText(getActivity(), "Welcome!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }
}
