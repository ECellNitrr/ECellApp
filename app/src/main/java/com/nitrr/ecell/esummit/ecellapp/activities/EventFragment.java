package com.nitrr.ecell.esummit.ecellapp.activities;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;

public class EventFragment extends Fragment {

    private String eventname;
    private TextView event;
    private TextView eventdis;
    private String eventdisc;
    private ImageView eventimg;
    private TextView venue;
    private TextView timefeild;

    public EventFragment() {
    }

    public static EventFragment newInstance(String param1, String param2) {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_event, container, false);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            eventname = bundle.get("eventname").toString();
            initalize(view);
        }
        return view;
    }

    private void initalize(View v) {
        event = v.findViewById(R.id.event_name);
        event.setText(eventname);
        eventimg = v.findViewById(R.id.event_img);
        eventdis = v.findViewById(R.id.event_text);
        venue = v.findViewById(R.id.event_venue);
        timefeild = v.findViewById(R.id.date_time);
    }

    private void setTime(String time,String date){
        time="Date: "+date+" | Time: "+time;
        timefeild.setText(time);
    }
}
