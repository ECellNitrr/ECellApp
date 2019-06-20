package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nitrr.ecell.esummit.ecellapp.R;


public class SpeakerFragment extends Fragment {

    private static Integer img;
    private static Integer pos;

    public static SpeakerFragment newInstance(int position,Integer image) {
        SpeakerFragment fragment = new SpeakerFragment();
        Bundle args = new Bundle();
        pos = position;
        img = image;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker, container, false);
        ImageView imageView = view.findViewById(R.id.speaker_img);
        imageView.setImageResource(img);
        return  view;
    }
}
