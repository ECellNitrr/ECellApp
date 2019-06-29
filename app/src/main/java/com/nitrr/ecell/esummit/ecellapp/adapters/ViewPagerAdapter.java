package com.nitrr.ecell.esummit.ecellapp.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nitrr.ecell.esummit.ecellapp.fragments.SpeakerFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter  extends FragmentStatePagerAdapter {
    private List<Integer> img = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<Integer> img) {
        super(fm);
        this.img = img;
    }

    @Override
    public Fragment getItem(int i) {
        return SpeakerFragment.newInstance(i,img.get(i));
        }

    @Override
    public int getCount() {
        return img.size();
    }
}
