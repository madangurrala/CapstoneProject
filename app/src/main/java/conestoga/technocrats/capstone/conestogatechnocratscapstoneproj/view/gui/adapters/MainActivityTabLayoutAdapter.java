package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivityTabLayoutAdapter extends FragmentStatePagerAdapter
{
    private List<Fragment> fragments=null;

    public MainActivityTabLayoutAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        fragments=new ArrayList<>();
    }

    public void addItem(Fragment fragment)
    {
        fragments.add(fragment);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
