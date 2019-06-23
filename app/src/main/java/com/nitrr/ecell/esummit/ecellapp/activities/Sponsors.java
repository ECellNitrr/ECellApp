package com.nitrr.ecell.esummit.ecellapp.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.SponsViewPagerAdapter;

public class Sponsors extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        initialize();
        setTabs();
    }

    private void setTabs() {

        pager.setAdapter(new SponsViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager,true);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initialize() {
        tabLayout = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);
    }
}
