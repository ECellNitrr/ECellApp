package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitrr.ecell.esummit.ecellapp.R;

public class ChangeNumberFragment extends Fragment {

    public ChangeNumberFragment() {
        // Required empty public constructor
    }
    public static ChangeNumberFragment newInstance() {
        return new ChangeNumberFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_number, container, false);
        return v;
    }
}
