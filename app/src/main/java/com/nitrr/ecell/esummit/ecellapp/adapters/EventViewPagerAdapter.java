package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.nitrr.ecell.esummit.ecellapp.fragments.EventRegister;
import com.nitrr.ecell.esummit.ecellapp.fragments.EventDataFragment;

public class EventViewPagerAdapter extends FragmentStatePagerAdapter {

    private String tabname[] = {"Overview","Venue and Register"};
    private Bundle bundle;

    public EventViewPagerAdapter(FragmentManager fm, Bundle bundle) {
        super(fm);
        this.bundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new EventDataFragment().getInstance(bundle, position);
        }
        else if(position==1)
            return new EventRegister().getInstance(bundle, position);
        return null;
    }

    @Override
    public int getCount() {
        return tabname.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        return obj;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabname[position];
    }
}
