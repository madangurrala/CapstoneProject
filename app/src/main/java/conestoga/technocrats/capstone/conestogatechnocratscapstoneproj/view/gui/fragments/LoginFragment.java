package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments;

import android.content.Intent;
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
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.sp.HelpShowCaseSP;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.LoginAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.AskAccountActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities.MainActivity;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.ILoginContract;
import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

public class LoginFragment extends Fragment implements View.OnClickListener, ILoginContract {

    private int showCaseStep=0;


    private LoginAccountPresenter loginAccountPresenter = null;

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
        loginAccountPresenter = new LoginAccountPresenter(getActivity().getApplicationContext(), this);

        UserBL userBL = new UserBL(getActivity().getApplicationContext());
        UserTO loginUserTO = userBL.fetchLoginAccountSP();
        //todo remove these lines later
        if (loginUserTO == null) {
            loginUserTO = new UserTO();
        }
        loginUserTO.setEmail("farshad.farshad@gmail.com");
        //loginUserTO.setEmail("madan.madan@gmail.com");
        loginUserTO.setPasswd("test");
        if (loginUserTO.getEmail() != null) {
            editEmail.setText(loginUserTO.getEmail());
            editPasswd.setText(loginUserTO.getPasswd());
        }
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

    @OnClick({R.id.btnLogin, R.id.btnSignUp})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin: {
                UserTO userTO = new UserTO();
                userTO.setEmail(editEmail.getText().toString());
                userTO.setPasswd(editPasswd.getText().toString());
                loginAccountPresenter.validateUserData(userTO);
                break;
            }
            case R.id.btnSignUp: {
                if (getActivity() != null && getActivity() instanceof AskAccountActivity) {
                    AskAccountActivity askAccountActivity = (AskAccountActivity) getActivity();
                    askAccountActivity.getAskAccountPresenter().showRightFragment(askAccountActivity.getSignUpAccountFragment(), getResources().getString(R.string.sign_up));
                }
                break;
            }
        }
    }

    @Override
    public void isUserDataValid(boolean status, UserTO userTO) {
        if (status) {
            loginAccountPresenter.loginUser(userTO);
        }
    }

    @Override
    public void userLoginStatus(boolean status, UserTO userTO) {
        if (!status) {
            Toast.makeText(getActivity(), "Sorry, Try Again!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(UserTO.KEY.ID_KEY, userTO.getId());
        startActivity(intent);
        getActivity().finish();
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
                    startHelpShowCase(btnLogin,"Login To Your Account","Login into your account and start using application!");
                    break;
                }
                case 1:
                {
                    startHelpShowCase(btnSignUp,"Sign Up A New Account","Make your own account and use it anytime, anywhere!");
                    break;
                }
            }
            return false;
        }
    });
}
