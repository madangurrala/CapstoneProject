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

import java.util.Random;

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

        // TODO: 24/03/20 Remove these lines
        editName.setText("Name Test");
        editFamily.setText("Family Test");
        editEmail.setText(String.format("%s%d%s","user.test",new Random().nextInt(500),"@gmail.com"));
        editPhone.setText("123456789");
        editPasswd.setText("pass123");
        return rootFrameLayout;
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
            Toast.makeText(getActivity(), "Try again!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getActivity(), "Give right data!", Toast.LENGTH_SHORT).show();
        }
        signUpAccountPresenter.registerUser(userTO);
    }
}
