package com.nitrr.ecell.esummit.ecellapp.activities;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
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
import com.nitrr.ecell.esummit.ecellapp.adapters.ViewPagerAdapter;
import com.nitrr.ecell.esummit.ecellapp.fragments.SpeakerFragment;
import com.nitrr.ecell.esummit.ecellapp.misc.ViewPagerDepthTransformer;

import java.util.ArrayList;
import java.util.List;

public class ESummitActivity extends AppCompatActivity{

    private ScrollView view;
    private ViewPager pager;
    private PagerAdapter adapter;
    private int[] pagebg = new int[4];
    private List<Integer> img = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esummit);
        initialize();
    }

    public void initialize() {
        pager = findViewById(R.id.pager);
        pager.setPageTransformer(true, new ViewPagerDepthTransformer());
        img.add(R.drawable.ic_username);
        img.add(R.drawable.button_sign_in);
        img.add(R.drawable.common_google_signin_btn_icon_dark_normal_background);
        img.add(R.drawable.common_google_signin_btn_icon_light);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),img);
        pager.setAdapter(adapter);
    }
}
