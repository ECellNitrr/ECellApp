package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.EventActivity;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPDialogFragment extends Fragment implements View.OnClickListener {

    private TextView otp1, otp2, otp3, otp4;
    private String otp, email;
    private Button n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, back, confirm;
    private DialogInterface.OnClickListener refreshListener = (dialog, which) -> APICall(),
            cancelListener = (dialog, which) -> {
        dialog.cancel();
        getActivity().onBackPressed();
    },
            listener;

    public OTPDialogFragment(String email,DialogInterface.OnClickListener listener){
        this.email = email;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_otp,container,false);
        initialize(view);
        return view;
    }

    private void initialize(View v){
        otp1 = v.findViewById(R.id.otp1);
        otp2 = v.findViewById(R.id.otp2);
        otp3 = v.findViewById(R.id.otp3);
        otp4 = v.findViewById(R.id.otp4);
        n1 = v.findViewById(R.id.no1);
        n2 = v.findViewById(R.id.no2);
        n3 = v.findViewById(R.id.no3);
        n4 = v.findViewById(R.id.no4);
        n5 = v.findViewById(R.id.no5);
        n6 = v.findViewById(R.id.no6);
        n7 = v.findViewById(R.id.no7);
        n8 = v.findViewById(R.id.no8);
        n9 = v.findViewById(R.id.no9);
        n0 = v.findViewById(R.id.no0);
        back = v.findViewById(R.id.back);
        confirm = v.findViewById(R.id.confirm);
        n1.setOnClickListener(this);
        n2.setOnClickListener(this);
        n3.setOnClickListener(this);
        n4.setOnClickListener(this);
        n5.setOnClickListener(this);
        n6.setOnClickListener(this);
        n7.setOnClickListener(this);
        n8.setOnClickListener(this);
        n9.setOnClickListener(this);
        n0.setOnClickListener(this);
        back.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.no0:
                update(0);
                break;
            case R.id.no1:
                update(1);
                break;
            case R.id.no2:
                update(2);
                break;
            case R.id.no3:
                update(3);
                break;
            case R.id.no4:
                update(4);
                break;
            case R.id.no5:
                update(5);
                break;
            case R.id.no6:
                update(6);
                break;
            case R.id.no7:
                update(7);
                break;
            case R.id.no8:
                update(8);
                break;
            case R.id.no9:
                update(9);
                break;
            case R.id.back:
                update(-1);
                break;
            case R.id.confirm:
                confirmOTP();
                break;
        }
    }

    private void confirmOTP() {
        otp = otp1.getText().toString()+otp2.getText()+otp3.getText()+otp4.getText();
    }

    private void update(int n){
        if(n==-1){
            if(otp2.getText()=="_")
                otp1.setText("_");
            else if(otp3.getText()=="_")
                otp2.setText("_");
            else if(otp4.getText()=="_")
                otp3.setText("_");
        }
        else if(otp1.getText()=="_")
            otp1.setText(n);
        else if(otp2.getText()=="_")
            otp2.setText(n);
        else if(otp3.getText()=="_")
            otp3.setText(n);
        else if(otp4.getText()=="_")
            otp4.setText(n);
        }

    private void APICall(){
        Call<String> call = AppClient.getInstance().createService(APIServices.class).sendOTP(email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(getContext()!=null)
                    if(response.isSuccessful()){
                        String otp = response.body();
                        if(otp!=null)
                            setConfirmed();
                        else
                            Utils.showDialog(getContext(),null,true,"Verification failed","","Retry",refreshListener,"Cancel",cancelListener);
                    }
                else
                        Utils.showDialog(getContext(),null,false,"Server is down","Data wasn't able to load","Retry",refreshListener,"Cancel",cancelListener);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if(getContext()!=null){
                    if(Utils.isNetworkAvailable(getContext()))
                        Utils.showDialog(getContext(),null,false,"No Internet Connection","Please try again","Retry",refreshListener,"Cancel",cancelListener);
                    else {
                        Utils.showShortToast(getContext(),"Something went wrong");
                    }

                }
            }
        });
    }

    boolean check(){
        if(otp=="")
            return true;
        return false;
    }

    private void setConfirmed(){
        Utils.showDialog(getContext(),null,false,"Verified",null,"Next",listener,null,null);
    }
}
