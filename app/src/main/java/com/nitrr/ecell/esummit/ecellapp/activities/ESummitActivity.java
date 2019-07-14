package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.ViewPagerAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.ESummitAnimaiton;
import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReciver;
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
    private BroadcastReceiver receiver;
    private IntentFilter filter;

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

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new NetworkChangeReciver();
        filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGED");
        registerReceiver(receiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        if(receiver !=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
        super.onDestroy();
    }

    public void initialize() {
        pager = findViewById(R.id.pager);
        additem(1,"Vire","https://www.whatsappprofiledpimages.com/wp-content/uploads/2018/07/cool-profile-pictures5-300x300.jpg");
        additem(2,"AAA","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8QrjD1Nnuez1NBnRg-fHvWAj1RCK9nUYDwC1ZTsLODmj4acwiuQ");
        additem(3,"BBB","https://www.biography.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cg_face%2Cq_auto:good%2Cw_300/MTQ3NTI2OTA4NzY5MjE2MTI4/drake_photo_by_prince_williams_wireimage_getty_479503454.jpg");
        additem(4,"CCC","https://i.ytimg.com/vi/CrEPmg06HRs/hqdefault.jpg");
        additem(5,"DDD","https://cdn.cnn.com/cnnnext/dam/assets/160725131446-graham-car-crash-evolved-human-full-169.jpeg");
        pager.setPageTransformer(true, new ViewPagerDepthTransformer());
        pager.setOffscreenPageLimit(2);
//        adapter = new ViewPagerAdapter(this,list);
        pager.setAdapter(adapter);
        toAboutES.setVisibility(View.INVISIBLE);
        toAboutES.setClickable(false);
    }

    void additem(int id,String name,String img){
        SpeakerDetails data = new SpeakerDetails(id,name,img);
        list.add(data);
    }
}
