package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.AskAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.ProfilePresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IProfileContract;

public class ProfileActivity extends AppCompatActivity implements IProfileContract {

    private ProfilePresenter profilePresenter=null;

    private FragmentManager fragmentManager=null;
    @BindView(R.id.profile_frame_content)
    public FrameLayout profile_frame_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        profilePresenter =new ProfilePresenter(this, this);
        profilePresenter.showRightFragment(null);
    }

    public ProfilePresenter getProfilePresenter()
    {
        return profilePresenter;
    }

    @Override
    public void showFragment(Fragment fragment)
    {
        if(fragmentManager==null)
        {
            fragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.profile_frame_content, fragment);
        fragmentTransaction.commit();
    }
}
