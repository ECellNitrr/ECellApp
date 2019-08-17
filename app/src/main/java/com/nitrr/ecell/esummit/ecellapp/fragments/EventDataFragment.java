package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.nitrr.ecell.esummit.ecellapp.R;

public class EventDataFragment extends Fragment {

    public EventDataFragment getInstance(Bundle bundle, int pos) {
        EventDataFragment fragment = new EventDataFragment();
        bundle.putInt("position",pos);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arg = this.getArguments();
        View view = inflater.inflate(R.layout.layout_event_details, container, false);
        TextView text = view.findViewById(R.id.event_text);
        if(arg!=null){
            text.setText(arg.getString("event_details"),null);
        }
        return view;
    }

}
