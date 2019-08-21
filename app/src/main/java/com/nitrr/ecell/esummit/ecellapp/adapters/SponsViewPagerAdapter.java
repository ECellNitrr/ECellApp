package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nitrr.ecell.esummit.ecellapp.fragments.SponsorsFragment;

public class SponsViewPagerAdapter extends FragmentPagerAdapter {

    private String tabName[];
    private Bundle[] bundle;
    private int[] index;

    public SponsViewPagerAdapter(FragmentManager fm, Bundle[] bundle,String[] tabsName,int[] index) {
        super(fm);
        this.bundle = bundle;
        this.index = index;
        this.tabName = tabsName;
    }

    @Override
    public Fragment getItem(int i) {
        return new SponsorsFragment().getInstance(bundle[i], index[i], i);
    }

    @Override
    public int getCount() {
        return tabName.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabName[position];
    }
}
