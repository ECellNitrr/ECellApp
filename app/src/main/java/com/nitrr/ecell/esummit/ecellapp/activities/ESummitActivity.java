package com.nitrr.ecell.esummit.ecellapp.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.SpeakerFragment;

public class ESummitActivity extends AppCompatActivity{

    private ScrollView view;
    private ViewPager pager;
    private PagerAdapter adapter;
    private int[] pagebg = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esummit);
        initialize();
    }

    public void initialize() {
        //view = findViewById(R.id.view);
        pager = findViewById(R.id.pager);
        adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pagerbg[0]=R.drawable
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return new SpeakerFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
