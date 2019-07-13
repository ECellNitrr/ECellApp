package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventData;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventModel;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventFragment extends Fragment {

    private EventModel model;
    private TextView event;
    private TextView eventditails;
    private ImageView eventimg;
    private TextView venue;
    private TextView timefeild;
    private List<EventData> list;
    private int position;



    private DialogInterface.OnClickListener cancellistener = (dialog, which) -> getActivity().finish();
    private DialogInterface.OnClickListener refreshlistener = (dialog, which) -> APICall();

    public EventFragment() {
    }

    public static EventFragment newInstance(String param1, int pos) {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();
        args.putInt("position", pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
            initalize(view);
        }
        APICall();
        return view;
    }

    void APICall() {
        APIServices service = AppClient.getRetrofitInstance();
        Call<EventModel> call = service.getEventDetails();
        call.enqueue(new Callback<EventModel>() {
            @Override
            public void onResponse(Call<EventModel> call, Response<EventModel> response) {
                if (response.isSuccessful()) {
                    model = response.body();
                    if (model != null) {
                        list = model.getList();
                        setData();
                    } else
                        Log.e("model empty", "model is empty and response is: " + response.toString());
                } else
                    Utils.showDialog(getContext(), null, false, null, getContext().getString(R.string.wasntabletoload), "Retry", refreshlistener, "Cancel", cancellistener);
            }

            @Override
            public void onFailure(Call<EventModel> call, Throwable t) {
                Log.e("Failure", "throwable is " + t + " and call is " + call.toString());
                if (!Utils.isNetworkAvailable(getContext()))
                    Utils.showDialog(getContext(), null, false, getContext().getString(R.string.no_internet), getContext().getString(R.string.wasntabletoload), "Retry", refreshlistener, "Cancel", cancellistener);
                else {
                    Utils.showLongToast(getContext(), "Something went wrong.");
                }
            }
        });
    }

    private void initalize(View v) {
        event = v.findViewById(R.id.event_name);
        eventimg = v.findViewById(R.id.event_img);
        eventditails = v.findViewById(R.id.event_text);
        venue = v.findViewById(R.id.event_venue);
        timefeild = v.findViewById(R.id.date_time);
    }

    private void setData() {
        EventData data = list.get(position);
        if (data.isFlag()) {
            event.setText(data.getName());
            Glide.with(getContext()).load(data.getImage()).into(eventimg);
            eventditails.setText(data.getDetails());
            timefeild.setText(setTime(data.getTime(), data.getDate()));
            venue.setText(data.getVenue());
        } else {
            Utils.showLongToast(getContext(), "" + R.string.eventdenied);
        }
    }

    private String setTime(String time, String date) {
        time = "Date: " + date + " | Time: " + time;
        return time;
    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
