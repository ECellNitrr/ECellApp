package com.nitrr.ecell.esummit.ecellapp.fragments.aboutUs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitrr.ecell.esummit.ecellapp.R;

public class Aim extends Fragment {

    public Aim() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aim, container, false);
    }

}
