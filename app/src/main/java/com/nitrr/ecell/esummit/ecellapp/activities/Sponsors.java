package com.nitrr.ecell.esummit.ecellapp.activities;

import android.graphics.Color;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.widget.ImageView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.SponsViewPagerAdapter;

public class Sponsors extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    Toolbar toolbar;
    ImageView circle1;
    ImageView circle2;
    ImageView circle3;
    ImageView circle4;
    ImageView circle5;

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
                if(position==0){
                    toolbar.setBackgroundColor(color(1,12,84,129,129,129,positionOffset));
                    circle1.setColorFilter(color(203, 239,255,255,255,255,positionOffset));
                    circle2.setColorFilter(color(173,218,238,239,239,239,positionOffset));
                    circle3.setColorFilter(color(147,203,227,225,225,225,positionOffset));
                    circle4.setColorFilter(color(119,196,230,209,209,209,positionOffset));
                    circle5.setColorFilter(color(90,177,216,188,188,188,positionOffset));
                }
                else if(position==1){
                    toolbar.setBackgroundColor(color(129,129,129,130,87,0,positionOffset));
                    circle1.setColorFilter(color(255,255,255,255,228,164,positionOffset));
                    circle2.setColorFilter(color(239,239,239,255,222,129,positionOffset));
                    circle3.setColorFilter(color(225,225,225,255,212,110,positionOffset));
                    circle4.setColorFilter(color(209,209,209,255,191,109,positionOffset));
                    circle5.setColorFilter(color(188,188,188,255,164,46,positionOffset));
                }
                else if(position==2){
                    toolbar.setBackgroundColor(Color.rgb(130,87,0));
                    circle1.setColorFilter(Color.rgb(255,228,164));
                    circle2.setColorFilter(Color.rgb(255,222,129));
                    circle3.setColorFilter(Color.rgb(255,212,110));
                    circle4.setColorFilter(Color.rgb(255,191,109));
                    circle5.setColorFilter(Color.rgb(255,164,46));
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
    }

    private void initialize() {
        tabLayout = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);
        toolbar = findViewById(R.id.toolbar);
        circle1 = findViewById(R.id.spons_bgcircle_1);
        circle2 = findViewById(R.id.spons_bgcircle_2);
        circle3 = findViewById(R.id.spons_bgcircle_3);
        circle4 = findViewById(R.id.spons_bgcircle_4);
        circle5 = findViewById(R.id.spons_bgcircle_5);
    }

    int colorValue(int Initial,int Final,float pos){
        return (int)(Final*pos+Initial*(1-pos));
    }

    int color(int IR,int IG,int IB,int FR,int FG,int FB,float pos){
        return Color.rgb(colorValue(IR,FR,pos),colorValue(IG,FG,pos),colorValue(IB,FB,pos));
    }
}
