package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.app.AlertDialog;
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
import com.nitrr.ecell.esummit.ecellapp.models.OTPVerification;
import com.nitrr.ecell.esummit.ecellapp.models.VerifyOTP;
import com.nitrr.ecell.esummit.ecellapp.models.forgotPassword.ForgotPassword;
import com.nitrr.ecell.esummit.ecellapp.models.forgotPassword.ForgotVerifyOTP;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPDialogFragment extends Fragment{

    private TextView otp1, otp2, otp3, otp4;
    private String otp = "";
    private String email;
    private GenericMessage msg;
    private SharedPref pref;

    private DialogInterface.OnClickListener resendOTPListener = ((dialog, which) -> verifyResendOTP());

    private DialogInterface.OnClickListener retryListener = ((dialog, which) -> {
        if (email == null)
            dialog.dismiss();
        else
            dialog.dismiss();
    });

    private DialogInterface.OnClickListener cancelListenerForgot = (dialog, which) -> {
        dialog.cancel();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    };

    private DialogInterface.OnClickListener nextListener = (dialog, which) -> {
        if(email == null) {
            pref.setIsVerifying(getContext(), false);
            pref.setMobileVerified(getContext(), true);
            pref.setIsLoggedIn(getContext(), true);
            Intent intent = new Intent(getContext(), HomeActivity.class);
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();
        } else {
            //Forgot Password Path
            ChangePasswordFragment fragment = new ChangePasswordFragment();
            Bundle b = new Bundle();
            b.putString("otp", otp);
            b.putString("email", email);
            fragment.setArguments(b);
            Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_outer_constraint, fragment, "change_pass")
                    .addToBackStack("otp")
                    .commit();
        }
    };

    public OTPDialogFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp, container, false);
        //Change Number OnClick Listener!
        view.findViewById(R.id.otp_edit_number).setOnClickListener(view1 -> {
            DialogInterface.OnClickListener yesListener;
            if(!pref.isLoggedIn(getContext())) {
                yesListener = (dialogInterface, i) ->
                        Objects.requireNonNull(getActivity())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.login_outer_constraint, new ChangeNumberFragment(), "change_number")
                                .addToBackStack("otp_register")
                                .commit();
            } else {
                yesListener = (dialogInterface, i) ->
                        Objects.requireNonNull(getActivity())
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.home_parent_layout, new ChangeNumberFragment(), "change_number")
                                .addToBackStack("otp_register")
                                .commit();
            }

            DialogInterface.OnClickListener noListener = (dialogInterface, i) -> dialogInterface.dismiss();
            //Alert Dialog to confirm change number
            Utils.showDialog(getContext(), null, true, "Change Mobile Number",
                    "Do you wish to change your registered mobile number?",
                    "Yes", yesListener, "No", noListener);
        });
        view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        Bundle bundle = getArguments();
        initialize(view, this);
        if (bundle != null) {
            //Forgot Pass Path
            view.findViewById(R.id.otp_mobile_number_layout).setVisibility(View.GONE);
            if(bundle.getString("email", null) != null) {
                email = bundle.getString("email");
                Log.e("OTPFrag", "Email has been received, Changing Password");
            } else {
                Utils.showShortToast(getContext(), "An Error occurred. Please Try Again");
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        } else {
            //Verify Mobile Number Path
            view.findViewById(R.id.otp_mobile_number_layout).setVisibility(View.VISIBLE);
            Log.e("OTPFrag", "Null has been received, Verifying OTP");
            pref.setIsVerifying(getActivity(), true);
        }
        return view;
    }

    private void initialize(View v, OTPDialogFragment fragment) {
        pref = new SharedPref();
        otp1 = v.findViewById(R.id.otp1);
        otp2 = v.findViewById(R.id.otp2);
        otp3 = v.findViewById(R.id.otp3);
        otp4 = v.findViewById(R.id.otp4);
        TextView resend = v.findViewById(R.id.resend_otp);
        resend.setOnClickListener(view -> {
            if(email==null)
                verifyResendOTP();
            else
                forgotPasswordResendOTP();
        });
        TextView mobileNumber = v.findViewById(R.id.otp_mobile_number);
        mobileNumber.setText(pref.getMobileNumber(getContext()));

        List<String> list = new ArrayList<>();
        list.add("1");list.add("2");list.add("3");
        list.add("4");list.add("5");list.add("6");
        list.add("7");list.add("8");list.add("9");
        list.add("Back");list.add("0");list.add("Confirm");

        RecyclerView recyclerView = v.findViewById(R.id.otp_recycler);
        if(getContext() != null) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(gridLayoutManager);
            OTPAdapter adapter = new OTPAdapter(getContext(), list, fragment);
            recyclerView.setAdapter(adapter);
        }
    }

    public void update(int n) {
        if (n == -1) {
            if (otp4.getText().toString().contentEquals("-"))
                if (otp3.getText().toString().contentEquals("-"))
                    if(otp2.getText().toString().contentEquals("-"))
                        otp1.setText("-");
                    else
                        otp2.setText("-");
                else
                    otp3.setText("-");
            else
                otp4.setText("-");
        }
        else if (n == -2) {
            confirmOTP();
        }
        else if (otp1.getText().toString().contentEquals("-"))
            otp1.setText("" + n);
        else if (otp2.getText().toString().contentEquals("-"))
            otp2.setText("" + n);
        else if (otp3.getText().toString().contentEquals("-"))
            otp3.setText("" + n);
        else if (otp4.getText().toString().contentEquals("-"))
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

            Utils.showShortToast(getContext(),"Wrong OTP.");
        }
    }

    private void verifyResendOTP() {
        String s = new SharedPref().getAccessToken(getActivity());
        Call<GenericMessage> call = AppClient.getInstance()
                .createService(APIServices.class)
                .resendOtp(s, Objects.requireNonNull(getContext()).getString(R.string.app_access_token));

        call.enqueue(new Callback<GenericMessage>() {
            @Override
            public void onResponse(@NonNull Call<GenericMessage> call, @NonNull Response<GenericMessage> response) {
                if (response.isSuccessful() && getContext() != null) {
                    msg = response.body();
                    if (msg != null) {
                        Utils.showLongToast(getContext(), msg.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericMessage> call, @NonNull Throwable t) {
                if (getContext() != null) {
                    if (!Utils.isNetworkAvailable(getContext()))
                        Utils.showDialog(getContext(), null, false, "No Internet Connection",
                                "Please try again", "Retry", resendOTPListener, null, null);
                    else {
                        Utils.showShortToast(getContext(), "Something went wrong, Please Try Again...");
                    }
                }
            }
        });
    }

    private void verifyOTPAPICall() {
        AlertDialog dialog = Utils.showProgressBar(getContext(),"Verifying OTP...");
        SharedPref pref = new SharedPref();
        String token = pref.getAccessToken(getContext());
        VerifyOTP verifyOTP = new VerifyOTP();
        verifyOTP.setOtp(otp);
        Call<OTPVerification> call = AppClient.getInstance().createServiceWithAuth(APIServices.class, getActivity())
                .verifyOtp(getContext().getString(R.string.app_access_token), token, verifyOTP);
        call.enqueue(new Callback<OTPVerification>() {
            @Override
            public void onResponse(@NonNull Call<OTPVerification> call, @NonNull Response<OTPVerification> response) {
                dialog.dismiss();
                if (getContext() != null)
                    if (response.isSuccessful()) {
                        OTPVerification otp = response.body();
                        if (otp != null) setConfirmed();
                        else
                            Utils.showDialog(getContext(), null, true, "Verification Failed.", "Please Try Again...",
                                    "Retry", retryListener, null, null);
                    } else {
                        try {
                            if (response.errorBody() != null) {
                                JSONObject object = new JSONObject(response.errorBody().string());
                                Utils.showDialog(getContext(), null, false, "Incorrect OTP",
                                        "The OTP you entered was incorrect. Please try again",
                                        "Retry", retryListener, null, null);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            }
            @Override
            public void onFailure(@NonNull Call<OTPVerification> call, @NonNull Throwable t) {
                dialog.cancel();
                if (getContext() != null) {
                    {
                        if (!Utils.isNetworkAvailable(getContext()))
                            Utils.showDialog(getContext(), null, false, "No Internet Connection", "Please try again",
                                    "Retry", retryListener, null, null);
                        else {
                            Utils.showShortToast(getContext(), "Something went wrong");
                            Objects.requireNonNull(getActivity()).onBackPressed();
                        }
                    }
                }
            }
        });
    }

    //Forgot Password OTP Verification API Call
    private void forgotOTPAPICall() {
        AlertDialog dialog = Utils.showProgressBar(getContext(),"Verifying ...");
        Call<GenericMessage> call = AppClient.getInstance().createServiceWithAuth(APIServices.class, getActivity()).postForgotOPTVerify(Objects.requireNonNull(getActivity()).getString(R.string.app_access_token), new ForgotVerifyOTP(otp, email));
        call.enqueue(new Callback<GenericMessage>() {
            @Override
            public void onResponse(@NonNull Call<GenericMessage> call, @NonNull Response<GenericMessage> response) {
                dialog.dismiss();
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
                    try {
                        if (response.errorBody() != null) {
                            Log.e("ForgotOTPVerify", "response unsuccessful: " + response.errorBody().string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Utils.showLongToast(getContext(), "OTP didn't match");
                }
            }
            @Override
            public void onFailure(@NonNull Call<GenericMessage> call, @NonNull Throwable t) {
                dialog.cancel();
                if (getContext() != null) {
                    {
                        if (!Utils.isNetworkAvailable(getContext()))
                            Utils.showDialog(getContext(), null, false, "No Internet Connection",
                                    "Please try again", "Retry", retryListener,
                                    "Cancel", cancelListenerForgot);
                        else {
                            Utils.showShortToast(getContext(), "Something went wrong");
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }
                    }
                }
            }
        });
    }

    private void forgotPasswordResendOTP() {
        ForgotPassword emailObject = new ForgotPassword(email);
        Call<GenericMessage> call = AppClient.getInstance().createService(APIServices.class).postEmailVerify(getContext().getString(R.string.app_access_token), emailObject);
        call.enqueue(new Callback<GenericMessage>() {
            @Override
            public void onResponse(@NonNull Call<GenericMessage> call, @NonNull Response<GenericMessage> response) {
                if(response.isSuccessful() && getContext() != null) {
                    if(response.body() != null) {
                        Utils.showShortToast(getContext(), response.body().getMessage());
                    } else {

                    }
                } else {
                    try {
                        if (response.errorBody() != null) {
                            JSONObject object = new JSONObject(response.errorBody().string());
                            if(object.getString("message") != null)
                                Utils.showShortToast(getContext(), object.getString("message"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericMessage> call, @NonNull Throwable t) {
                DialogInterface.OnClickListener retryListener = (dialogInterface, i) -> {
                    forgotPasswordResendOTP();
                    dialogInterface.dismiss();
                };

                DialogInterface.OnClickListener cancelListener = (dialogInterface, i) -> {
                    Fragment fragment = Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentByTag("verify_email");
                    if (fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                    dialogInterface.dismiss();
                };

                if(!Utils.isNetworkAvailable(Objects.requireNonNull(getContext()))) {
                    Utils.showDialog(getContext(), null, false, "Network Error",
                            "There was a Connection Error. Make sure you have a stable connection", "Retry",
                            retryListener, "Cancel", cancelListener);
                }
                else {
                    Utils.showShortToast(getContext(),"Something went wrong");
                    Fragment fragment = Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentByTag("verify_email");
                    if (fragment != null) {
                        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                }
            }
        });
    }

    private void setConfirmed() {
        if(getContext()!=null){
            SharedPref pref = new SharedPref();
            pref.setMobileVerified(getContext(),true);
            pref.setIsVerifying(getContext(), false);
            Utils.showDialog(getContext(), null, false, "Verified", "OTP Verified Successfully",
                    "Next", nextListener, null, null);
        }
    }
}
