package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReciver;

public class EventFragment extends Fragment {

    private TextView event;
    private TextView eventditails;
    private ImageView eventimg;
    private TextView venuefeild;
    private TextView timefeild;
    private BroadcastReceiver receiver;
    private IntentFilter filter;

    public EventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            initalize(view);
            setData(bundle.getString("event_name"),
                    bundle.getString("event_image"),
                    bundle.getString("event_details"),
                    bundle.getString("event_time"),
                    bundle.getString("event_date"),
                    bundle.getString("event_venue"));
        }
        return view;
    }


    private void initalize(View v) {
        event = v.findViewById(R.id.event_name);
        eventimg = v.findViewById(R.id.event_img);
        eventditails = v.findViewById(R.id.event_text);
        venuefeild = v.findViewById(R.id.event_venue);
        timefeild = v.findViewById(R.id.date_time);
    }

    private void setData(String name,String image,String details,String time,String date,String venue) {

            try{
                if(image!=null){
                Glide.with(getContext()).load(image).into(eventimg);}}
                catch(Exception e){
                    setData(name,image,details,time,date,venue);
                }
                event.setText(name);
                eventditails.setText(details);
                timefeild.setText(setTime(time,date));
                venuefeild.setText(venue);
    }

    private String setTime(String time, String date) {
        time = "Date: " + date + " | Time: " + time;
        return time;
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new NetworkChangeReciver();
        filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGED");
        getContext().registerReceiver(receiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    @Override
    public void onDestroy() {
        if(receiver !=null){
            getContext().unregisterReceiver(receiver);
            receiver=null;
        }
        super.onDestroy();
    }
}
