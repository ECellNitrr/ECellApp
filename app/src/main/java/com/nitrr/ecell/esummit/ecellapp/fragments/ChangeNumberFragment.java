package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.models.verifyNumber.ChangeNumber;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeNumberFragment extends Fragment {

    private EditText number;
    private TextInputLayout numberLayout;

    private DialogInterface.OnClickListener successfulYes = (dialogInterface, i) -> {
        dialogInterface.dismiss();
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_parent_layout, new OTPDialogFragment())
                .addToBackStack(null)
                .commit();
    };
    private DialogInterface.OnClickListener successfulNo = (dialogInterface, i) -> {
        dialogInterface.dismiss();
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .addToBackStack(null)
                .commit();
    };

    public ChangeNumberFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_number, container, false);
        number = v.findViewById(R.id.new_number);
        numberLayout = v.findViewById(R.id.new_number_layout);
        apiCall();
        return v;
    }

    private void apiCall() {
        Utils.showDialog(getContext(), null, true, "Mobile Number Changed Successfully!", "Do you wish to verify your new number?",
                "Yes", successfulYes, "No", successfulNo);
        String num = number.getText().toString();
        ChangeNumber object = new ChangeNumber(num);
        SharedPref pref = new SharedPref();

        AlertDialog dialog = Utils.showProgressBar(getContext(), "Changing Number...");

        Call<GenericMessage> call = AppClient.getInstance().createService(APIServices.class)
                .changeNumber(getResources().getString(R.string.app_access_token),
                        pref.getAccessToken(getContext()), object);

        call.enqueue(new Callback<GenericMessage>() {
            @Override
            public void onResponse(@NonNull Call<GenericMessage> call, @NonNull Response<GenericMessage> response) {
                dialog.dismiss();
                if (getContext() != null) {
                    if(response.isSuccessful()) {
                        if(response.body() != null) {

                            pref.setMobileVerified(getActivity(), false);
                        } else {

                        }
                    } else {
                        if(response.errorBody() != null) {
                            try {
                                Utils.showLongToast(getContext(), response.errorBody().string().split("\"")[2]);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    Utils.showLongToast(getContext(), "Something went wrong!");
                }

            }

            @Override
            public void onFailure(@NonNull Call<GenericMessage> call, @NonNull Throwable t) {

            }
        });
    }
}

//                            Utils.showLongToast(getContext(), response.body().getMessage());
//                                    Objects.requireNonNull(getActivity()).getSupportFragmentManager()
//                                    .beginTransaction()
//                                    .remove(ChangeNumberFragment.this)
//                                    .commit();