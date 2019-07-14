package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.EventRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReciver;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventData;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventModel;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {
    private EventModel model;
    private List<EventData> list= new ArrayList<EventData>();
    EventRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    private DialogInterface.OnClickListener refreshlistener= (dialog, which) -> APICall();
    private DialogInterface.OnClickListener cancellistener = (dialog, which) -> {
        dialog.cancel();
        EventActivity.this.finish();
    };
    private BroadcastReceiver receiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        recyclerView = findViewById(R.id.event_recycler);
        APICall();
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new NetworkChangeReciver();
        filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGED");
        registerReceiver(receiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        if(receiver !=null){
        unregisterReceiver(receiver);
        receiver=null;
        }
        super.onDestroy();
    }

    void APICall(){
        APIServices services = AppClient.getInstance().createService(APIServices.class);
        Call<EventModel> call = services.getEventDetails();
        call.enqueue(new Callback<EventModel>() {
            @Override
            public void onResponse(Call<EventModel> call, Response<EventModel> response) {
                if(response.isSuccessful() && EventActivity.this!=null){
                    Log.e("response",response.toString());
                    model = response.body();

                    if(model!=null){
                        list = model.getList();
                        setRecycler();
                    }
                    else
                        Utils.showDialog(EventActivity.this,null,false,"Something went load",getApplicationContext().getString(R.string.wasntabletoload),"Retry",refreshlistener,"Cancel",cancellistener);
                }
                else{
                    try {
                        Log.e("error body====::::",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Utils.showDialog(EventActivity.this,null,false,"Server Down",getApplicationContext().getString(R.string.wasntabletoload),"Retry",refreshlistener,"Cancel",cancellistener);
                }
            }
            @Override
            public void onFailure(Call<EventModel> call, Throwable t) {
                if(EventActivity.this!=null){
                    if(!Utils.isNetworkAvailable(getApplicationContext()))
                        Utils.showDialog(EventActivity.this,null,false,"Poor Internet Connection",getApplicationContext().getString(R.string.wasntabletoload),"Retry",refreshlistener,"Cancel",cancellistener);
                    else
                    {Log.e("Failure:  =","throwable is "+t);
                        Utils.showLongToast(getApplicationContext(),"Something went wrong.");
                    }
                }
            }
        });
    }

    private void setRecycler() {
        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new EventRecyclerViewAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}
