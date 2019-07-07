package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.EventRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.EventData;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Event extends AppCompatActivity {
    private List<EventData> list= new ArrayList<EventData>();
    EventRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    private DialogInterface.OnClickListener refreshlistener= new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            APICall();
        }
    };
    private DialogInterface.OnClickListener cancellistener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            Event.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        recyclerView = findViewById(R.id.event_recycler);
        APICall();
    }

    private void setRecycler() {
        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new EventRecyclerViewAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }

    void APICall(){
        APIServices services = AppClient.getRetrofitInstance();
        Call<List<EventData>> call = services.getEventDetails();
        call.enqueue(new Callback<List<EventData>>() {
            @Override
            public void onResponse(Call<List<EventData>> call, Response<List<EventData>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    if(list!=null && !list.isEmpty())
                        setRecycler();
                    else
                        Utils.showDialog(getApplicationContext(),null,false,null,getApplicationContext().getString(R.string.wasntabletoload),"Retry",refreshlistener,"Cancel",cancellistener);
                }
                else
                    Utils.showDialog(getApplicationContext(),null,false,null,getApplicationContext().getString(R.string.wasntabletoload),"Retry",refreshlistener,"Cancel",cancellistener);
            }
            @Override
            public void onFailure(Call<List<EventData>> call, Throwable t) {
                if(!Utils.isNetworkAvailable(getApplicationContext()))
                    Utils.showDialog(Event.this,null,false,"Poor Internet Connection",getApplicationContext().getString(R.string.wasntabletoload),"Retry",refreshlistener,"Cancel",cancellistener);
                else
                {Utils.showToast(getApplicationContext(),"Something went wrong.");
                Event.this.finish();
                }
            }
        });
    }

}
