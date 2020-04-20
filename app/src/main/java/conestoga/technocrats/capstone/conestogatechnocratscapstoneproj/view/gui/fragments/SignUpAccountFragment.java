package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.sp.HelpShowCaseSP;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.SignUpAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.AskAccountActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.MainActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ISignUpAccountContract;
import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

public class SignUpAccountFragment extends Fragment implements View.OnClickListener, ISignUpAccountContract {
    private int showCaseStep=0;
    private SignUpAccountPresenter signUpAccountPresenter = null;
    private FrameLayout rootFrameLayout;
    @BindView(R.id.btnSignUp)
    public Button btnSignUp;
    @BindView(R.id.btnLogin)
    public Button btnLogin;
    @BindView(R.id.editName)
    public TextInputEditText editName;
    @BindView(R.id.editFamily)
    public TextInputEditText editFamily;
    @BindView(R.id.editEmail)
    public TextInputEditText editEmail;
    @BindView(R.id.editPhone)
    public TextInputEditText editPhone;
    @BindView(R.id.editPasswd)
    public TextInputEditText editPasswd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootFrameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_signup_account, container, false);
        ButterKnife.bind(this, rootFrameLayout);
        signUpAccountPresenter = new SignUpAccountPresenter(getActivity().getApplicationContext(),this);
        showCaseStep=0;
        return rootFrameLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(new HelpShowCaseSP(getActivity()).isFirstLaunch())
        {
            handlerShowCase.sendEmptyMessageDelayed(showCaseStep,500);
        }
    }

    @OnClick({R.id.btnSignUp,R.id.btnLogin})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp: {
                UserTO userTO = new UserTO();
                userTO.setName(editName.getText().toString());
                userTO.setFamily(editFamily.getText().toString());
                userTO.setEmail(editEmail.getText().toString());
                userTO.setPhone(editPhone.getText().toString());
                userTO.setPasswd(editPasswd.getText().toString());
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
        if (!status) {
            Toast.makeText(getActivity(), "Sorry, there is a problem, please try again", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent(getActivity(),MainActivity.class);
        intent.putExtra(UserTO.KEY.ID_KEY,userTO.getId());
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void userDataValidationStatus(boolean status, UserTO userTO) {
        if (!status) {
            Toast.makeText(getActivity(), "Please enter some valid data and try again", Toast.LENGTH_LONG).show();
        }
        signUpAccountPresenter.registerUser(userTO);
    }

    @Override
    public void signUpEmailInValid() {
        editEmail.setError("Please enter a valid email address");
    }

    @Override
    public void signUpPasswordInValid() {
        editPasswd.setError("Please enter a valid password with at least 5 characters");
    }


    private void startHelpShowCase(View targetView,String title,String desc) {
        new GuideView.Builder(getActivity())
                .setTitle(title)
                .setContentText(desc)
                .setTargetView(targetView)
                //.setContentTypeFace(Typeface)//optional
                //.setTitleTypeFace(Typeface)//optional
                .setDismissType(DismissType.outside) //optional - default dismissible by TargetView
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        if(showCaseStep==0)
                        {
                            ++showCaseStep;
                            handlerShowCase.sendEmptyMessageDelayed(1,500);
                        }
                    }
                })
                .build()
                .show();
    }

    private Handler handlerShowCase=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case 0:
                {
                    startHelpShowCase(btnSignUp,"Sign Up A New Account","Make your own account and use it anytime, anywhere!");
                    break;
                }
                case 1:
                {
                    startHelpShowCase(btnLogin,"Login To Your Account","Login into your account and start using application!");
                    break;
                }

            }
            return false;
        }
    });
}
