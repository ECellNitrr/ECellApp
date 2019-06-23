package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nitrr.ecell.esummit.ecellapp.activities.SponsorsFragment;

public class SponsViewPagerAdapter extends FragmentPagerAdapter {

    private String tabname[] = {"Associate Sponsors","Platinum Sponsors", "Gold sponsors"};

    public SponsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new SponsorsFragment().newInstance(tabname[i],i);
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
