package com.nitrr.ecell.esummit.ecellapp.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nitrr.ecell.esummit.ecellapp.fragments.SponsorsFragment;

public class SponsViewPagerAdapter extends FragmentPagerAdapter {

    private String tabname[] = { "Associate","Platinum", "Gold"};

    public SponsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) { return new SponsorsFragment().newInstance(i);
    }

    @Override
    public int getCount() {
        return tabname.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabname[position];
    }
}
