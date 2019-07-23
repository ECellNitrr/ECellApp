package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.nitrr.ecell.esummit.ecellapp.activities.HomeActivity;
import com.nitrr.ecell.esummit.ecellapp.activities.LoginActivity;
import com.nitrr.ecell.esummit.ecellapp.adapters.OTPAdapter;
import com.nitrr.ecell.esummit.ecellapp.fragments.forgotPassword.ChangePasswordFragment;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.models.MessageModel;
import com.nitrr.ecell.esummit.ecellapp.models.VerifyOTP;
import com.nitrr.ecell.esummit.ecellapp.models.forgotPassword.ForgotVerifyOTP;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPDialogFragment extends Fragment implements View.OnClickListener {

    private TextView otp1, otp2, otp3, otp4, resend;
    private String otp = "";
    private String email;
    private List<String> list = new ArrayList<>();
    private MessageModel msg;

    private DialogInterface.OnClickListener resendOTPListener = ((dialog, which) -> resendOTP());

    private DialogInterface.OnClickListener retryListener = ((dialog, which) -> {
        if (email == null)
            verifyOTPAPICall();
        else
            forgotOTPAPICall();
    });

    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> {
        dialog.cancel();
        if (email != null) {
            //Verify OTP Path
            startActivity(new Intent(getActivity(), HomeActivity.class));
        }
        else {
            //Forgot Password Path
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    };

    private DialogInterface.OnClickListener nextListener = (dialog, which) -> {
        if(email == null) {
            //Verify OTP Path
            //TODO add confirm code
        } else {
            //Forgot Password Path
            ChangePasswordFragment fragment = new ChangePasswordFragment();
            Bundle b = new Bundle();
            b.putString("otp", otp);
            b.putString("email", email);
            fragment.setArguments(b);
            Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_outer_constraint, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    };
    public OTPDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_otp, container, false);
        Bundle bundle = getArguments();
        OTPDialogFragment fragment = this;
        initialize(view,fragment);
        if (bundle != null) {
            if(bundle.getString("email", null) != null) {
                email = bundle.getString("email", null);
                Log.e("OTPFrag", "Email has been received, Changing Password");
            } else {
                Log.e("OTPFrag", "Null has been received, Verifying OTP");
                resendOTP();
            }
        } else {
            Utils.showShortToast(getContext(), "An Error occurred. Please Try Again");
            if(new SharedPref().isLoggedIn(getActivity())) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
            } else {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        }
        return view;
    }

    private void initialize(View v,OTPDialogFragment fragment) {
        otp1 = v.findViewById(R.id.otp1);
        otp2 = v.findViewById(R.id.otp2);
        otp3 = v.findViewById(R.id.otp3);
        otp4 = v.findViewById(R.id.otp4);
        resend = v.findViewById(R.id.resend_otp);

        resend.setOnClickListener(this);

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("Back");
        list.add("0");
        list.add("Confirm");

        RecyclerView recyclerView = v.findViewById(R.id.otp_recycler);
        if(getContext() != null) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(gridLayoutManager);
            OTPAdapter adapter = new OTPAdapter(getContext(), list,fragment);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.resend_otp:
                resendOTP();
                break;
        }
    }

    public void update(int n) {
        if (n == -1) {
            if (otp4.getText().toString().contentEquals("  "))
                if (otp3.getText().toString().contentEquals("  "))
                    if (otp2.getText().toString().contentEquals("  "))
                        otp1.setText(" ");
                    else
                        otp2.setText(" ");
                else
                    otp3.setText(" ");
            else
                otp4.setText(" ");
        }
        else if (n == -2) {
            confirmOTP();
        }
        else if (otp1.getText().toString().contentEquals(" "))
            otp1.setText("" + n);
        else if (otp2.getText().toString().contentEquals(" "))
            otp2.setText("" + n);
        else if (otp3.getText().toString().contentEquals(" "))
            otp3.setText("" + n);
        else if (otp4.getText().toString().contentEquals(" "))
            otp4.setText("" + n);
    }

    private void confirmOTP() {
        if (!(otp4.getText().toString().contentEquals("_"))) {
            otp = otp1.getText().toString() + otp2.getText() + otp3.getText() + otp4.getText();
            if(email == null)
                verifyOTPAPICall();
            else
                forgotOTPAPICall();
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
                .resendOtp(new SharedPref().getAccessToken(getContext()), getActivity().getResources().getString(R.string.app_access_token));

        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(@NonNull Call<MessageModel> call, @NonNull Response<MessageModel> response) {
                if (response.isSuccessful() && getContext() != null) {
                    msg = response.body();
                    if (msg == null) {
                        Utils.showLongToast(getContext(), msg.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageModel> call, @NonNull Throwable t) {
                if (getContext() != null) {
                    if (!Utils.isNetworkAvailable(getContext()))
                        Utils.showDialog(getContext(), null, false, "No Internet Connection", "Please try again", "Retry", resendOTPListener, "Cancel", cancelListener);
                    else {
                        Utils.showShortToast(getContext(), "Something went wrong");
                        Objects.requireNonNull(getActivity()).onBackPressed();
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
                            Utils.showDialog(getContext(), null, true, "Verification Failed.", "", "Retry", retryListener, "Cancel", cancelListener);
                    } else
                        Utils.showDialog(getContext(), null, false, "There was an issue.", "Data wasn't able to load", "Retry", retryListener, "Cancel", cancelListener);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (getContext() != null) {
                    {
                        if (!Utils.isNetworkAvailable(getContext()))
                            Utils.showDialog(getContext(), null, false, "No Internet Connection", "Please try again", "Retry", retryListener, "Cancel", cancelListener);
                        else {
                            Utils.showShortToast(getContext(), "Something went wrong");
                            getActivity().onBackPressed();
                        }
                    }
                }
            }
        });
    }

    //Forgot Password OTP Verification API Call
    private void forgotOTPAPICall() {
        Call<GenericMessage> call = AppClient.getInstance().createServiceWithAuth(APIServices.class, getActivity()).postForgotOPTVerify(new ForgotVerifyOTP(otp, email));
        call.enqueue(new Callback<GenericMessage>() {
            @Override
            public void onResponse(@NonNull Call<GenericMessage> call, @NonNull Response<GenericMessage> response) {
                if(response.isSuccessful() && getContext() != null) {
                    if (response.body() != null) {
                        Log.e("ForgotOTPVerify", "response body received");
                        //Successful
                        setConfirmed();
                    } else {
                        Log.e("ForgotOTPVerify", "response body null");
                        Utils.showShortToast(getContext(), "Something Went Wrong, Please Try Again");
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }
                } else {
                    Log.e("ForgotOTPVerify", "response unsuccessful");
                    Utils.showLongToast(getContext(), "OTP didn't match");
                }
            }
            @Override
            public void onFailure(@NonNull Call<GenericMessage> call, @NonNull Throwable t) {
                if (getContext() != null) {
                    {
                        if (!Utils.isNetworkAvailable(getContext()))
                            Utils.showDialog(getContext(), null, false, "No Internet Connection",
                                    "Please try again", "Retry", retryListener,
                                    "Cancel", cancelListener);
                        else {
                            Utils.showShortToast(getContext(), "Something went wrong");
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }
                    }
                }
            }
        });
    }

    private void setConfirmed() {
        Utils.showDialog(getContext(), null, false, "Verified", "OTP Verified Successfully",
                "Next", nextListener, null, null);
    }
}
