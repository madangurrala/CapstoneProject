package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
<<<<<<< HEAD

=======
>>>>>>> fa22d9277ed3e9a61e92af5e79d5713a2f2dff26
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters.MainActivityTabLayoutAdapter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.AppointmentsFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.ChatListFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.ProfileFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.PropertiesFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IMainContract;

public class MainActivity extends AppCompatActivity implements IMainContract, ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {


<<<<<<< HEAD
    private MainActivityTabLayoutAdapter tabLayoutAdapter=null;
    private List<String> titles=new ArrayList<>();
    private int viewPagerDefaultIndex=0;
=======
    private MainActivityTabLayoutAdapter tabLayoutAdapter = null;
    private List<String> titles = new ArrayList<>();
    private int viewPagerDefaultIndex = 0;
>>>>>>> fa22d9277ed3e9a61e92af5e79d5713a2f2dff26


    @BindView(R.id.tabLayout)
    public TabLayout tabLayout;
    @BindView(R.id.viewPager)
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

        new UserBL(this).fetchLoginAccountSP().getEmail();

=======
>>>>>>> fa22d9277ed3e9a61e92af5e79d5713a2f2dff26
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.app_name));
        tabLayoutAdapter = new MainActivityTabLayoutAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        initFragments();
        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_view_comfy);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_chat);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_event_available);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_person);
        viewPager.setCurrentItem(viewPagerDefaultIndex);
        updateTitle(titles.get(viewPagerDefaultIndex));
        viewPager.addOnPageChangeListener(this);
        tabLayout.addOnTabSelectedListener(this);
    }

    private void initFragments() {
        tabLayoutAdapter.addItem(new PropertiesFragment());
        titles.add("Properties List");
        tabLayoutAdapter.addItem(new ChatListFragment());
        titles.add("My Messages");
        tabLayoutAdapter.addItem(new AppointmentsFragment());
        titles.add("My Appointments");
        tabLayoutAdapter.addItem(new ProfileFragment());
        titles.add("My Profile");
    }

    @Override
    public void updateTitle(String title) {
        setTitle(title);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        updateTitle(titles.get(position));
        tabLayout.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
<<<<<<< HEAD
=======
        new UserBL(this).fetchLoginAccountSP().getEmail();
>>>>>>> fa22d9277ed3e9a61e92af5e79d5713a2f2dff26
    }
}