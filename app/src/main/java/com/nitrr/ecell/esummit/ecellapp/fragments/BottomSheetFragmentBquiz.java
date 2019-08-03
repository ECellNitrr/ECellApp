package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nitrr.ecell.esummit.ecellapp.R;

public class BottomSheetFragmentBquiz extends BottomSheetDialogFragment{

    public static BottomSheetFragmentBquiz newInstance() {
        return new BottomSheetFragmentBquiz();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet_fragment_bquiz, container,
                false);



        return view;

    }
}