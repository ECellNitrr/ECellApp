package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nitrr.ecell.esummit.ecellapp.R;

public class OTPDialog extends Fragment {

    private static OTPDialog otpDialog;
    private EditText otp1, otp2, otp3, otp4;
    private String otp;
    Button n1, n2, n3, n4, n5, n6;

    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> dialog.cancel();
    private DialogInterface.OnClickListener confirmListener = (dialog, which) -> {
        otp = otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() + otp4.getText().toString();
        dialog.dismiss();
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_otp,container,false);
        initialize(view);
        return view;
    }

    public void initialize(View v){
        otp1 = v.findViewById(R.id.otp1);
        otp2 = v.findViewById(R.id.otp2);
        otp3 = v.findViewById(R.id.otp3);
        otp4 = v.findViewById(R.id.otp4);

        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1)
                    otp2.requestFocus();
            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count==0){
                }
                Log.e("textchanged called","this function is called");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1)
                    otp3.requestFocus();
                else if (s.length() == 0)
                    otp1.requestFocus();
            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1)
                    otp4.requestFocus();
                else if (s.length() == 0)
                    otp2.requestFocus();
            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (otp4.length() == 0)
                    otp3.requestFocus();
                else
                    otp4.clearFocus();
            }
        });

        String otp = otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() + otp4.getText().toString();

    }
}
