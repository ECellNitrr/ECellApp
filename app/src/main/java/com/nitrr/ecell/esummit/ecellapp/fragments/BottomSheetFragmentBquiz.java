package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nitrr.ecell.esummit.ecellapp.R;

public class BottomSheetFragmentBquiz extends BottomSheetDialogFragment {

    private TextView messageTextView;

    public static BottomSheetFragmentBquiz newInstance() {
        return new BottomSheetFragmentBquiz();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_fragment_bquiz, container, false);
        messageTextView = view.findViewById(R.id.bquiz_bottom_sheet_message);

        if (getDialog().getWindow() != null)
            getDialog().getWindow().setDimAmount(0.8f);

        return view;
    }

    public void setMessage(String message) {
        if (message != null)
            messageTextView.setText(message);
    }

}