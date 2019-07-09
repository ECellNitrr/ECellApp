package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.ViewPagerAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.ESummitAnimaiton;
import com.nitrr.ecell.esummit.ecellapp.misc.ViewPagerDepthTransformer;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.SpeakerDetails;

import java.util.ArrayList;
import java.util.List;

public class ESummitActivity extends AppCompatActivity{

    private View view;
    private ViewPager pager;
    private PagerAdapter adapter;
    private int[] pagebg = new int[4];
    private List<SpeakerDetails> list = new ArrayList<>();
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
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        toAboutES.setVisibility(View.INVISIBLE);
        toAboutES.setClickable(false);
    }

    void additem(int id,String name,String img){
        SpeakerDetails data = new SpeakerDetails(id,name,img);
        list.add(data);
    }
}
