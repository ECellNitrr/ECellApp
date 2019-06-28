package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.nitrr.ecell.esummit.ecellapp.R;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    public BottomSheetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hamburgerbottomsheet,container,false);
        LinearLayout aboutUs = v.findViewById(R.id.about_us);
        LinearLayout signOut = v.findViewById(R.id.sign_out);
        ImageButton x = v.findViewById(R.id.close);

        aboutUs.setOnClickListener((View vv) -> {
             //TODO(1) about us intent need to be added
             });
        signOut.setOnClickListener((View vv) -> {
            //TODO(2) sign out action needs to be implemented
            });
        x.setOnClickListener((View vv) -> dismiss());
        return v;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
