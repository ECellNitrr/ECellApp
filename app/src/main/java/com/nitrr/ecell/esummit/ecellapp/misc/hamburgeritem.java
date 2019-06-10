package com.nitrr.ecell.esummit.ecellapp.misc;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nitrr.ecell.esummit.ecellapp.R;

public class hamburgeritem extends BottomSheetDialogFragment {
    private ButtomSheetListener buttomSheetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hamburgerbottomsheet,container,false);
        Button aboutus = v.findViewById(R.id.about_us);
        Button signout = v.findViewById(R.id.sign_out);

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO(1) about us intent need to be added
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO(2) signout action needs to be implemented
            }
        });

        return v;
    }

    public interface ButtomSheetListener{
        void OnClicked(String string);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
        buttomSheetListener = (ButtomSheetListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" must implement bottom sheet listener");
        }
    }
}
