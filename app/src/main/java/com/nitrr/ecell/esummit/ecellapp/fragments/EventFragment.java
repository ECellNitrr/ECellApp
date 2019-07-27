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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;

import java.util.Objects;

import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReceiver;

public class EventFragment extends Fragment {

    private TextView event;
    private TextView eventDetails;
    private ImageView eventImage;
    private TextView venueField;
    private TextView timeField;
    private BroadcastReceiver receiver;

    public EventFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        ImageView back = view.findViewById(R.id.eventfrag_back);
        back.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());
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
        eventImage = v.findViewById(R.id.event_img);
        eventDetails = v.findViewById(R.id.event_text);
        venueField = v.findViewById(R.id.event_venue);
        timeField = v.findViewById(R.id.date_time);
    }

    private void setData(String name, String image, String details, String time, String date, String venue) {

        try {
            if (image != null) {
                Glide.with(Objects.requireNonNull(getContext())).load(image).into(eventImage);
            }
        } catch (Exception e) {
            setData(name, image, details, time, date, venue);
        }
        event.setText(name);
        eventDetails.setText(details);
        timeField.setText(setTime(time, date));
        venueField.setText(venue);
    }

    private String setTime(String time, String date) {
        time = "Date: " + date + " | Time: " + time;
        return time;
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGED");
        Objects.requireNonNull(getContext()).registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    @Override
    public void onDestroy() {
        if (receiver != null) {
            Objects.requireNonNull(getContext()).unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }
}
