package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nitrr.ecell.esummit.ecellapp.fragments.SponsorsFragment;
import com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsRVData;

public class SponsViewPagerAdapter extends FragmentPagerAdapter {

    private String tabname[] = { "Associate","Platinum", "Gold"};
    private Bundle[] bundle;
    private int[] index;

    public SponsViewPagerAdapter(FragmentManager fm, Bundle[] bundle,int[] index) {
        super(fm);
        this.bundle = bundle;
        this.index = index;
    }

    @Override
    public Fragment getItem(int i) {
        return new SponsorsFragment().newInstance(bundle[i],index[i]);
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
