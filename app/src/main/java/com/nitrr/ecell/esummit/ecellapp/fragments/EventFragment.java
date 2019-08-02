package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReceiver;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventRegistrationModel;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventFragment extends Fragment {

    private TextView event;
    private TextView eventDetails;
    private ImageView eventImage;
    private TextView venueField;
    private TextView timeField;
    private BroadcastReceiver receiver;
    private String eventName, id;

    public EventFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        ImageView back = view.findViewById(R.id.eventfrag_back);
        back.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            initalize(view);
            eventName = bundle.getString("event_name");
            id = bundle.getString("id");
            setData(bundle.getString("event_name"),
                    bundle.getString("event_img"),
                    bundle.getString("event_details"),
                    bundle.getString("event_time"),
                    bundle.getString("event_date"),
                    bundle.getString("event_venue"));
        }
        return view;
    }


    private void initalize(View v) {
        event = v.findViewById(R.id.event_name);
        eventImage = v.findViewById(R.id.event_img);
        eventDetails = v.findViewById(R.id.event_text);
        venueField = v.findViewById(R.id.event_venue);
        timeField = v.findViewById(R.id.date_time);
        Button register = v.findViewById(R.id.event_register_button);
        register.setOnClickListener(v1 -> registerAPI(id));
    }

    private void setData(String name, String image, String details, String time, String date, String venue) {

        try {
            if (image != null) {
                Glide.with(Objects.requireNonNull(getContext())).load(image).into(eventImage);
            }
        } catch (Exception e) {
            setData(name, image, details, time, date, venue);
        }
        event.setText(name);
        eventDetails.setText(details);
        timeField.setText(setTime(time, date));
        venueField.setText(venue);
    }

    private String setTime(String time, String date) {
        time = "Date: " + date + " | Time: " + time;
        return time;
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGED");
        Objects.requireNonNull(getContext()).registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onDestroy() {
        if (receiver != null) {
            Objects.requireNonNull(getContext()).unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }

    private void registerAPI( String id) {
        AlertDialog dialog = Utils.showProgressBar(getContext(),"Registring...");
        SharedPref pref = new SharedPref();
        Call<GenericMessage> call = AppClient.getInstance().createService(APIServices.class).registerForEvent(getContext().getString(R.string.app_access_token),pref.getAccessToken(getContext()),id);
        call.enqueue(new Callback<GenericMessage>() {
            @Override
            public void onResponse(Call<GenericMessage> call, Response<GenericMessage> response) {
                dialog.dismiss();
                if(getContext()!=null && response.isSuccessful()){
                    String msg = response.body().getMessage();
                    if(msg!=null){
                        Log.e("EvtReg. respons msg","Message is: "+msg);
                        Utils.showShortToast(getContext(),"Registration Successfull");
                    }
                    else {
                        try {
                            Log.e("EvtReg. empty response",response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GenericMessage> call, Throwable t) {
                dialog.cancel();
                if(!Utils.isNetworkAvailable(getContext()))
                    Utils.showShortToast(getContext(),"No Internet Connection");
                else
                    Utils.showShortToast(getContext(),"Something went Wrong");
                Log.e("EvtregFailure===","response is a failure");
            }
        });
    }
}
