package com.nitrr.ecell.esummit.ecellapp.activities;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.SponsViewPagerAdapter;

public class Sponsors extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    Toolbar toolbar;

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


        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==1){
                    toolbar.setBackgroundColor(Color.argb(40,20,20,20));
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
    }

    private void initialize() {
        tabLayout = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);
        toolbar = findViewById(R.id.toolbar);
    }
}
