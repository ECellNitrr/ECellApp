package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.nitrr.ecell.esummit.ecellapp.R;

import java.io.IOException;
import java.util.Objects;

import com.nitrr.ecell.esummit.ecellapp.adapters.EventRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.adapters.EventViewPagerAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReceiver;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventFragment extends Fragment {

    private BroadcastReceiver receiver;
    private ImageView eventImage;
    private String eventName;
    private ViewPager pager;
    private TabLayout tab;

    public EventFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        ImageView back = view.findViewById(R.id.eventfrag_back);
        back.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            initialize(view);
            eventName = bundle.getString("event_name");
            setData(bundle.getString("event_name"),
                    bundle.getString("event_img"));
        }
        tab = view.findViewById(R.id.event_tab);
        pager = view.findViewById(R.id.event_pager);
        pager.setAdapter(new EventViewPagerAdapter(getActivity().getSupportFragmentManager(), bundle));
        tab.setupWithViewPager(pager);
        return view;
    }

    private void initialize(View v) {
        eventImage = v.findViewById(R.id.event_img);
    }

    private void setData(String name, String image) {

        try {
            if (image != null) {
                CircularProgressDrawable progressDrawable = new CircularProgressDrawable(Objects.requireNonNull(getContext()));
                progressDrawable.setStrokeWidth(15f);
                progressDrawable.setCenterRadius(120f);
                progressDrawable.start();
                Glide.with(Objects.requireNonNull(getContext()))
                        .load(image)
                        .placeholder(progressDrawable)
                        .into(eventImage);
            }
        } catch (Exception e) {
            setData(name, image);
        }
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
            EventRecyclerViewAdapter.setSelected(false);
            receiver = null;
        }
        super.onDestroy();
    }

}
