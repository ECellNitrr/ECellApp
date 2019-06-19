package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nitrr.ecell.esummit.ecellapp.R;


public class SpeakerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView mParam1;
    private Integer mParam2;

    public static SpeakerFragment newInstance(/*ImageView img,*/ int pos) {
        SpeakerFragment fragment = new SpeakerFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, img);
        args.putInt(ARG_PARAM2, pos);
        fragment.setArguments(args);
        Log.e("SADF", "position of fragment is "+pos);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM2);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speaker, container, false);
    }
}
