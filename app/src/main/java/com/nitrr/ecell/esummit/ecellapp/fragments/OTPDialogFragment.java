package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.OTPAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.MessageModel;
import com.nitrr.ecell.esummit.ecellapp.models.VerifyOTP;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPDialogFragment extends Fragment{

    private TextView otp1, otp2, otp3, otp4;
    private String otp, email, prevFrag;
    private List<String> list = new ArrayList<>();
    private MessageModel msg;
    private DialogInterface.OnClickListener resendOTPListener = ((dialog, which) -> {
            resendOTP();
    });
    private DialogInterface.OnClickListener refreshListener = ((dialog, which) -> {
        if(email==null)
            verifyOTPAPICall();
        else
            forgotOTPAPICall();
    });
    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> {
        dialog.cancel();

        if (getActivity() != null)
            getActivity().onBackPressed();

    }, listener;

    public OTPDialogFragment() {
    }

    public OTPDialogFragment getInstance(DialogInterface.OnClickListener listener) {
        this.listener = listener;
        return new OTPDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        prevFrag = bundle.getString("prevfrag");
        View view = inflater.inflate(R.layout.layout_otp, container, false);
        if(prevFrag.equalsIgnoreCase("ForgotPassword"))
            email = bundle.getString("email");
        else if(prevFrag.equalsIgnoreCase("Home Activity"))
            resendOTP();
        initialize(view);
        return view;
    }

    private void initialize(View v) {
        otp1 = v.findViewById(R.id.otp1);
        otp2 = v.findViewById(R.id.otp2);
        otp3 = v.findViewById(R.id.otp3);
        otp4 = v.findViewById(R.id.otp4);

        additem("1");
        additem("2");
        additem("3");
        additem("4");
        additem("5");
        additem("6");
        additem("7");
        additem("8");
        additem("9");
        additem("Back");
        additem("0");
        additem("Confirm");

        RecyclerView recyclerView = v.findViewById(R.id.otp_recycler);
        if(getContext()!=null){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(gridLayoutManager);
            OTPAdapter adapter = new OTPAdapter(getContext(), list);
            recyclerView.setAdapter(adapter);
        }
    }

    void additem(String s){
        list.add(s);
    }

    public void update(int n) {
        if (n == -1) {
            if (otp4.getText().toString().contentEquals("_"))
                if (otp3.getText().toString().contentEquals("_"))
                    if (otp2.getText().toString().contentEquals("_"))
                        otp1.setText("_");
                    else
                        otp2.setText("_");
                else
                    otp3.setText("_");
            else
                otp4.setText("_");
        }
        else if (n == -2){
            OTPDialogFragment fragment = new OTPDialogFragment();
            fragment.confirmOTP();

        }
        else if (otp1.getText().toString().contentEquals("_"))
            otp1.setText("" + n);
        else if (otp2.getText().toString().contentEquals("_"))
            otp2.setText("" + n);
        else if (otp3.getText().toString().contentEquals("_"))
            otp3.setText("" + n);
        else if (otp4.getText().toString().contentEquals("_"))
            otp4.setText("" + n);
    }

    private void confirmOTP() {
        if (!(otp4.getText().toString().contentEquals("_"))) {
            otp = otp1.getText().toString() + otp2.getText() + otp3.getText() + otp4.getText();
            verifyOTPAPICall();
        } else {
            otp1.setText("_");
            otp2.setText("_");
            otp3.setText("_");
            otp4.setText("_");
            Utils.showShortToast(getContext(),"Please enter OTP correctly");
        }
    }

    private void resendOTP() {
        Call<MessageModel> call = AppClient.getInstance()
                .createService(APIServices.class)
                .resendOtp(new SharedPref().getAccessToken(getContext()),APIServices.access);

        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful() && getContext() != null) {
                    msg = response.body();
                    if (msg == null) {
                        Utils.showLongToast(getContext(),msg.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                if (getContext() != null) {
                    if (!Utils.isNetworkAvailable(getContext()))
                        Utils.showDialog(getContext(), null, false, "No Internet Connection", "Please try again", "Retry", resendOTPListener, "Cancel", cancelListener);
                    else {
                        Utils.showShortToast(getContext(), "Something went wrong");
                        getActivity().onBackPressed();
                    }
                }
            }
        });
    }

    private void verifyOTPAPICall() {
        SharedPref pref = new SharedPref();
        pref.getAccessToken(getContext());
        VerifyOTP OTP = new VerifyOTP();
        OTP.setOtp(otp);
        Call<String> call = AppClient.getInstance().createServiceWithAuth(APIServices.class, getActivity()).verifyOtp(OTP);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (getContext() != null)
                    if (response.isSuccessful()) {
                        String otp = response.body();
                        if (otp != null)
                            setConfirmed();
                        else
                            Utils.showDialog(getContext(), null, true, "Verification Failed.", "", "Retry", refreshListener, "Cancel", cancelListener);
                    } else
                        Utils.showDialog(getContext(), null, false, "There was an issue.", "Data wasn't able to load", "Retry", refreshListener, "Cancel", cancelListener);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (getContext() != null) {
                    {
                        if (!Utils.isNetworkAvailable(getContext()))
                            Utils.showDialog(getContext(), null, false, "No Internet Connection", "Please try again", "Retry", refreshListener, "Cancel", cancelListener);
                        else {
                            Utils.showShortToast(getContext(), "Something went wrong");
                            getActivity().onBackPressed();
                        }
                    }
                }
            }
        });
    }

    private void forgotOTPAPICall() {

        Call<MessageModel> call = AppClient.getInstance().createServiceWithAuth(APIServices.class, getActivity()).sendOtp(APIServices.access,email);
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                    if (response.isSuccessful() && getContext() != null) {
                        MessageModel otp = response.body();
                        if (otp != null)
                            setConfirmed();
                        else
                            Utils.showDialog(getContext(), null, true, "Verification failed", "", "Retry", refreshListener, "Cancel", cancelListener);
                    } else
                        Utils.showDialog(getContext(), null, false, "Server is down", "Data wasn't able to load", "Retry", refreshListener, "Cancel", cancelListener);
            }
            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                if (getContext() != null) {
                    {
                        if (!Utils.isNetworkAvailable(getContext()))
                            Utils.showDialog(getContext(), null, false, "No Internet Connection", "Please try again", "Retry", refreshListener, "Cancel", cancelListener);
                        else {
                            Utils.showShortToast(getContext(), "Something went wrong");
                            getActivity().onBackPressed();
                        }
                    }
                }
            }
        });
    }

    private void setConfirmed() {
        Utils.showDialog(getContext(), null, false, "Verified", null, "Next", listener, null, null);
    }
}
