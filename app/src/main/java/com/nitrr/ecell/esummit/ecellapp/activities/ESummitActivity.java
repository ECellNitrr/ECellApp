package com.nitrr.ecell.esummit.ecellapp.activities;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.ViewPagerAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.ESummitAnimaiton;
import com.nitrr.ecell.esummit.ecellapp.misc.ViewPagerDepthTransformer;

import java.util.ArrayList;
import java.util.List;

public class ESummitActivity extends AppCompatActivity{

    private View view;
    private ViewPager pager;
    private PagerAdapter adapter;
    private int[] pagebg = new int[4];
    private List<Integer> img = new ArrayList<>();
    ESummitAnimaiton animaiton;
    private TextView toSpeaker, toAboutES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esummit);
        animaiton = new ESummitAnimaiton(this);
        toSpeaker = findViewById(R.id.to_speaker);
        toSpeaker.setOnClickListener((View v) -> animaiton.toSpeakers());

        toAboutES = findViewById(R.id.to_about_es);
        toAboutES.setOnClickListener((View v) -> animaiton.toAboutES());

        initialize();
        view = findViewById(R.id.es_inner_constraint);
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

        toAboutES.setVisibility(View.INVISIBLE);
        toAboutES.setClickable(false);
    }
}
