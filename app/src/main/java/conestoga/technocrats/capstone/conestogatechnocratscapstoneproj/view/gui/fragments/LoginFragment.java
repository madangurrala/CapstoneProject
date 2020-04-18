package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.ProjApplication;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.LoginAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.AskAccountActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.MainActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.ProfileActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ILoginContract;

public class LoginFragment extends Fragment implements View.OnClickListener, ILoginContract {
    private LoginAccountPresenter loginAccountPresenter=null;

    private FrameLayout rootFrameLayout;
    @BindView(R.id.editEmail)
    public TextInputEditText editEmail;
    @BindView(R.id.editPasswd)
    public TextInputEditText editPasswd;
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
        loginAccountPresenter=new LoginAccountPresenter(getActivity().getApplicationContext(),this);

        UserBL userBL=new UserBL(getActivity().getApplicationContext());
        UserTO loginUserTO=userBL.fetchLoginAccountSP();
        //todo remove these lines later
        if(loginUserTO==null)
        {
            loginUserTO=new UserTO();
        }
        loginUserTO.setEmail("test2@gmail.com");
        loginUserTO.setPasswd("test");
        if(loginUserTO.getEmail()!=null)
        {
            editEmail.setText(loginUserTO.getEmail());
            editPasswd.setText(loginUserTO.getPasswd());
        }
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
                userTO.setEmail(editEmail.getText().toString());
                userTO.setPasswd(editPasswd.getText().toString());
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
        if(!status)
        {
            Toast.makeText(getActivity(), "Sorry, Try Again!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent(getActivity(),MainActivity.class);
        intent.putExtra(UserTO.KEY.ID_KEY,userTO.getId());
        startActivity(intent);
        getActivity().finish();
    }
}
