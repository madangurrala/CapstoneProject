package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.UserBL;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.utils.FirebaseUtil;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters.MainActivityTabLayoutAdapter;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.AppointmentsFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.ChatListFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.ProfileFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.fragments.PropertiesFragment;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IMainContract;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.impl.IMessageTask;

public class MainActivity extends AppCompatActivity implements IMainContract, ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    private FirebaseUtil firebaseUtil;

    private MainActivityTabLayoutAdapter tabLayoutAdapter = null;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private List<String> titles = new ArrayList<>();
    private int viewPagerDefaultIndex = 0;
    private FloatingActionButton postAddButton;
    private Intent postAdIntent;

    @BindView(R.id.tabLayout)
    public TabLayout tabLayout;
    @BindView(R.id.viewPager)
    public ViewPager viewPager;
    @BindView(R.id.nav_view)
    public NavigationView nav_view;

    public TextView txtUserEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        firebaseUtil=FirebaseUtil.getInstance(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        txtUserEmail=nav_view.getHeaderView(0).findViewById(R.id.txtUserEmail);
        //new UserBL(this).fetchLoginAccountSP().getEmail();
        postAddButton = findViewById(R.id.floatingButton);

        setTitle(getResources().getString(R.string.app_name));
        tabLayoutAdapter = new MainActivityTabLayoutAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_draw_open,
                R.string.nav_draw_close);

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        /*updateTabLayoutBadge(0, 10);
        updateTabLayoutBadge(1, 99);
        updateTabLayoutBadge(2, 2);*/

        postAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postAdIntent = new Intent(MainActivity.this, PostPropertyActivity.class);
                startActivity(postAdIntent);

            }
        });


        UserBL userBL=new UserBL(this);
        UserTO userTO=userBL.fetchLoginAccountSP();
        if(userTO!=null)
        {
            txtUserEmail.setText(userTO.getEmail());
        }
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

    public void updateTabLayoutBadge(int index, int number) {
        BadgeDrawable badgeDrawable_0 = tabLayout.getTabAt(index).getOrCreateBadge();
        badgeDrawable_0.setVisible(true);
        badgeDrawable_0.setNumber(number);
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
        // new UserBL(this).fetchLoginAccountSP().getEmail();
    }


    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

}