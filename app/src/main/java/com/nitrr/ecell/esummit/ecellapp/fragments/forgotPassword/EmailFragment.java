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
import com.nitrr.ecell.esummit.ecellapp.fragments.OTPDialogFragment;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.models.forgotPassword.ForgotPassword;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailFragment extends Fragment {

    private EditText email;
    private TextInputLayout layout;
    private String forgot = "Forgot Password";

    public EmailFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_email, container, false);

        email = v.findViewById(R.id.forgot_email);
        layout = v.findViewById(R.id.forgot_email_layout);

        MaterialButton verify = v.findViewById(R.id.forgot_verify);
        verify.getBackground().setColorFilter(this.getResources()
                .getColor(R.color.forgot_button), PorterDuff.Mode.MULTIPLY);

//        MaterialButton back = v.findViewById(R.id.forgot_back);

//        back.setOnClickListener(view -> {
//            startActivity(new Intent(getContext(), LoginActivity.class));
//        });

        ((TextView)v.findViewById(R.id.forgot_title)).setTypeface(Typeface
                .createFromAsset(Objects.requireNonNull(getContext()).getAssets(), "fonts/Oswald-Regular.ttf"));
        verify.setOnClickListener(view -> {

            apiCall();
//            OTPDialogFragment fragment = new OTPDialogFragment();
//            Bundle b = new Bundle();
//            b.putString("email", email.getText().toString());
//            fragment.setArguments(b);
//
//            Objects.requireNonNull(getActivity()).getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.login_outer_constraint, fragment)
//                    .remove(EmailFragment.this)
//                    .commit();
        });
        return v;
    }

    private void apiCall() {
        AlertDialog bar = Utils.showProgressBar(getContext(), "Verifying Email...");
        ForgotPassword emailObject = new ForgotPassword(this.email.getText().toString());

        if(!checkEmail(this.email, layout)) {
            bar.dismiss();
        } else {
            Call<GenericMessage> call = AppClient.getInstance().createService(APIServices.class).postEmailVerify(getContext().getString(R.string.app_access_token),emailObject);

            call.enqueue(new Callback<GenericMessage>() {
                @Override
                public void onResponse(@NonNull Call<GenericMessage> call, @NonNull Response<GenericMessage> response) {
                    if(response.isSuccessful() && getContext() != null) {
                        Log.e(forgot, "response successful");
                        if(response.body() != null) {
                            Log.e(forgot, "response body received and toasted");
                            bar.dismiss();
                            Utils.showShortToast(getContext(), response.body().getMessage());

                            OTPDialogFragment fragment = new OTPDialogFragment();
                            Bundle b = new Bundle();
                            b.putString("email", email.getText().toString());
                            fragment.setArguments(b);

                            Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.login_outer_constraint, fragment)
                                    .commit();

                        } else {
                            Log.e(forgot, "Response Body Null");
                            bar.dismiss();
                        }
                    } else {
                        try {
                            if (response.errorBody() != null) {
                                Log.e(forgot, "ErrorBodyPrinted");
                                Utils.showShortToast(getContext(), response.errorBody().string());
                                bar.dismiss();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<GenericMessage> call, @NonNull Throwable t) {
                    bar.dismiss();
                    DialogInterface.OnClickListener retryListener = (dialogInterface, i) -> {
                        apiCall();
                        dialogInterface.dismiss();
                    };
                    DialogInterface.OnClickListener cancelListener = (dialogInterface, i) -> {
                        Fragment fragment = Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentByTag("verify_email");
                        if (fragment != null) {
                            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        }
                        dialogInterface.dismiss();
                    };
                    if(!Utils.isNetworkAvailable(getContext())){
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

    }

    private boolean checkEmail(EditText editText, TextInputLayout layout){
        if(!isNotEmpty(editText, layout))
            return false;
        String email = editText.getText().toString();
        int check = email.length() - 1;
        boolean dot = false;
        Character character;
        while (check >= 0) {
            character = email.charAt(check);
            if (character.compareTo('.') == 0 && !dot) {
                dot = true;
                check--;
            }

            if (dot)
                if (character.compareTo('@') == 0)
                    return true;
            check--;
        }
        layout.setError("Invalid Email!");
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
