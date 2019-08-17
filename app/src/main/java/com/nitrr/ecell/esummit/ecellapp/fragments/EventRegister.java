package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRegister extends Fragment {


    private Button register;
    private String id, eventName;

    public EventRegister getInstance(Bundle bundle, int pos) {
        EventRegister fragment = new EventRegister();
        bundle.putInt("position", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arg = this.getArguments();
        View view = inflater.inflate(R.layout.layout_event_register, container, false);

        TextView venueField = view.findViewById(R.id.event_venue);
        TextView timeField = view.findViewById(R.id.date_time);
        register = view.findViewById(R.id.event_register_button);
        register.setOnClickListener(v1 -> registerAPI(id));
        if(arg!=null){
            if(arg.getBoolean("registered")) {
                register.setText(Objects.requireNonNull(getActivity()).getString(R.string.register));
                register.setEnabled(false);
            }
            eventName = arg.getString("event_name");
            id = arg.getString("id");
            timeField.setText(setTime(arg.getString("event_time"), arg.getString("event_date")));
            venueField.setText(arg.getString("event_venue"));
        }
        return view;
    }

    private String setTime(String time, String date) {
        time = "Date: " + date + " | Time: " + time;
        return time;
    }

    private void registerAPI( String id) {
        AlertDialog dialog = Utils.showProgressBar(getContext(),"Registering you for " + eventName);
        SharedPref pref = new SharedPref();
        Call<GenericMessage> call = AppClient.getInstance().createService(APIServices.class)
                .registerForEvent(getContext().getString(R.string.app_access_token), pref.getAccessToken(getContext()), id);

        call.enqueue(new Callback<GenericMessage>() {
            @Override
            public void onResponse(@NonNull Call<GenericMessage> call, @NonNull Response<GenericMessage> response) {
                dialog.dismiss();
                if (getContext() != null) {
                    if(response.isSuccessful()) {
                        if (response.body() != null) {
                            Utils.showLongToast(getContext(), "You have been Successfully Registered for " + eventName + ".\nDo Come!");
                            register.setText(Objects.requireNonNull(getActivity()).getString(R.string.register));
                            register.setEnabled(false);
                        } else {
                            Utils.showShortToast(getContext(), "There was an error on our side. PLease try again later.");
                        }
                    } else if(response.code() == 404) {
                        Utils.showLongToast(getContext(), "Couldn't Register you for this Event.");
                    } else {
                        Utils.showLongToast(getContext(), "Couldn't Register you for this Event.");
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<GenericMessage> call, @NonNull Throwable t) {
                dialog.dismiss();
                if(!Utils.isNetworkAvailable(getContext()))
                    Utils.showShortToast(getContext(),"No Internet Connection");
                else
                    Utils.showShortToast(getContext(),"Something went Wrong");
                Log.e("Event Registration","API call Failure :" + t.getMessage());
            }
        });
    }
}