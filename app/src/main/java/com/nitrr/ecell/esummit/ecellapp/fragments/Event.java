package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.Event.EventData;
import com.nitrr.ecell.esummit.ecellapp.models.Event.EventModel;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Event extends Fragment {

    private EventModel model;
    private TextView event;
    private TextView eventditails;
    private ImageView eventimg;
    private TextView venue;
    private TextView timefeild;
    private List<EventData> list;
    private int position;
    private DialogInterface.OnClickListener refreshlistener= new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            APICall();
        }
    };
    private DialogInterface.OnClickListener cancellistener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            getActivity().finish();
        }
    };

    public Event() {
    }

    public static Event newInstance(String param1, int pos) {
        Event fragment = new Event();
        Bundle args = new Bundle();
        args.putInt("position",pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_event, container, false);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            position =bundle.getInt("position");
            initalize(view);
        }
        APICall();
        return view;
    }

    void APICall(){
        APIServices service = AppClient.getRetrofitInstance();
        Call<EventModel> call= service.getEventDetails();
        call.enqueue(new Callback<EventModel>() {
            @Override
            public void onResponse(Call<EventModel> call, Response<EventModel> response) {
                if(response.isSuccessful()){
                    model = response.body();
                    if(model!=null){
                        list =model.getList();
                        setData();
                    }
                    else
                        Log.e("model empty","moldel is emple and response is: "+ response.toString());
                }
                else
                    Utils.showDialog(getContext(),null,false,null,getContext().getString(R.string.wasntabletoload),"Retry",refreshlistener,"Cancel",cancellistener);
            }
            @Override
            public void onFailure(Call<EventModel> call, Throwable t) {
                Log.e("Failure", "throwable is "+t+" and call is "+call.toString());
                if(!Utils.isNetworkAvailable(getContext()))
                    Utils.showDialog(getContext(),null,false,null,getContext().getString(R.string.wasntabletoload),"Retry",refreshlistener,"Cancel",cancellistener);
                else
                {Utils.showToast(getContext(),"Something went wrong.");
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

    private void setData(){
        EventData data =list.get(position);
        if(data.isFlag())
        {
        event.setText(data.getName());
        Glide.with(getContext()).load(data.getImage()).into(eventimg);
        eventditails.setText(data.getDetails());
        timefeild.setText(setTime(data.getTime(),data.getDate()));
        venue.setText(data.getVenue());
        }
        else{
            Utils.showToast(getContext(),""+R.string.eventdenied);
        }
    }

    private String setTime(String time,String date){
        time="Date: "+date+" | Time: "+time;
        return time;
    }
}
