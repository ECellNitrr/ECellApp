package com.nitrr.ecell.esummit.ecellapp.fragments.forgotPassword;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.LoginActivity;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.models.forgotPassword.ChangePassword;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordFragment extends Fragment {

    private EditText newPass, confirmPass;
    private TextInputLayout newPassLayout, confPassLayout;
    private String email, otp, change = "Change Password";

    public ChangePasswordFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);

        MaterialButton verify = v.findViewById(R.id. change_reset);
        verify.getBackground().setColorFilter(this.getResources().getColor(R.color.forgot_button), PorterDuff.Mode.MULTIPLY);
        ((TextView)v.findViewById(R.id.forgot_title)).setTypeface(Typeface.createFromAsset(Objects.requireNonNull(getContext())
                .getAssets(), "fonts/Oswald-Regular.ttf"));

        newPass = v.findViewById(R.id.new_pass);
        newPassLayout = v.findViewById(R.id.new_pass_layout);
        confirmPass = v.findViewById(R.id.confirm_pass);
        confPassLayout = v.findViewById(R.id.confirm_pass_layout);
        Bundle b = getArguments();
        if (b != null) {
            email = b.getString("email", "");
            otp = b.getString("otp", "");
        }

        verify.setOnClickListener(view -> apiCall());
        return v;
    }

    private void apiCall() {
        if(checkPassword(newPass, newPassLayout)) {
            if(newPass.getText().toString().equals(confirmPass.getText().toString())) {
                String password = newPass.getText().toString();

                AlertDialog bar = Utils.showProgressBar(getContext(), "Changing Password...");

                Call<GenericMessage> call = AppClient.getInstance().createService(APIServices.class)
                        .postPasswordChange(Objects.requireNonNull(getContext()).getResources().getString(R.string.app_access_token),
                                new ChangePassword(email, password, otp));

                call.enqueue(new Callback<GenericMessage>() {
                    @Override
                    public void onResponse(@NonNull Call<GenericMessage> call, @NonNull Response<GenericMessage> response) {
                        if(response.isSuccessful() && getContext() != null) {
                            Log.e(change, "response successful");
                            if(response.body() != null) {
                                Log.e(change, "response body received");
                                bar.dismiss();
                                Utils.showShortToast(getContext(), response.body().getMessage());
                                startActivity(new Intent(getContext(), LoginActivity.class));
                            } else {
                                Log.e(change, "Response Body Null");
                            }
                        } else {
                            try {
                                if (response.errorBody() != null) {
                                    Log.e(change, "ErrorBodyToasted");
                                    JSONObject object = new JSONObject(response.errorBody().string());
                                    if(object.getString("message") != null)
                                        Utils.showLongToast(getContext(), object.getString("message"));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GenericMessage> call, @NonNull Throwable t) {
                        if(getContext()!=null){
                            bar.dismiss();
                            DialogInterface.OnClickListener retryListener = (dialogInterface, i) -> {
                                apiCall();
                                dialogInterface.dismiss();
                            };

                            DialogInterface.OnClickListener cancelListener = (dialogInterface, i) -> {
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                dialogInterface.dismiss();
                            };
                            if(!Utils.isNetworkAvailable(getContext())){
                                Utils.showDialog(getContext(), null, false, "Network Unstable",
                                        "There was a Connection Error. Make sure you have a stable connection",
                                        "Retry", retryListener, "Cancel", cancelListener);
                            }
                            else{
                                Utils.showShortToast(getContext(),"Something went wrong");
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                            }
                        }
                    }
                });
            } else {
                confPassLayout.setError("Password Doesn't Match");
                confirmPass.setText("");
            }
        }
    }

    private boolean checkPassword(EditText editText, TextInputLayout layout) {
        if(!isNotEmpty(editText, layout)) {
            return false;
        }
        if (editText.getText().length() >= 8)
            return true;
        layout.setError("Required Min 8 Characters!");
        return false;
    }

    private boolean isNotEmpty(EditText editText, TextInputLayout layout){
        if(!TextUtils.isEmpty(editText.getText()))
            return true;
        else
            layout.setError("Field Required!");
        return false;
    }
}
