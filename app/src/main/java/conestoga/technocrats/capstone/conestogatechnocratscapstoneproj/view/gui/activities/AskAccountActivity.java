package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.presenter.AskAccountPresenter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IAskAccountContract;

public class AskAccountActivity extends AppCompatActivity implements IAskAccountContract {

    private AskAccountPresenter askAccountPresenter=null;

    private FragmentManager fragmentManager=null;
    @BindView(R.id.frame_content)
    public FrameLayout frame_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_account);
        ButterKnife.bind(this);
        askAccountPresenter=new AskAccountPresenter(this,this);
        askAccountPresenter.showRightFragment(null);
    }

    public AskAccountPresenter getAskAccountPresenter()
    {
        return askAccountPresenter;
    }

    @Override
    public void showFragment(Fragment fragment)
    {
        if(fragmentManager==null)
        {
            fragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, fragment);
        fragmentTransaction.commit();
    }
}
