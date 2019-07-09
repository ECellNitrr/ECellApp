package com.nitrr.ecell.esummit.ecellapp.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nitrr.ecell.esummit.ecellapp.fragments.SpeakerFragment;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.SpeakerDetails;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter  extends FragmentStatePagerAdapter {
    private List<SpeakerDetails> speaker = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<SpeakerDetails> speakerDetailsList) {
        super(fm);
        speaker = speakerDetailsList;
    }

    @Override
    public Fragment getItem(int i) {
        return SpeakerFragment.newInstance(i,speaker);
        }

    @Override
    public int getCount() {
        return speaker.size();
    }
}
