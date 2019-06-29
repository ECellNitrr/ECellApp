package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.EventRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.models.EventData;

import java.util.ArrayList;
import java.util.List;

public class Event extends AppCompatActivity {
    private List<EventData> list= new ArrayList<EventData>();
    EventRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        recyclerView = findViewById(R.id.event_recycler);
        initializeList();
        setRecycler();
    }

    private void setRecycler() {
        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new EventRecyclerViewAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }

    private void initializeList() {
        EventData data1=new EventData();
        data1.setName("CR");
        data1.setAlpha(0.2f);
        data1.setImage(""+R.drawable.ic_event_cr);
        list.add(data1);

        EventData data2=new EventData();
        data2.setName("BQ");
        data2.setAlpha(0.4f);
        data2.setImage(""+R.drawable.ic_event_bq);
        list.add(data2);

        EventData data3=new EventData();
        data3.setName("BC");
        data3.setAlpha(0.6f);
        data3.setImage(""+R.drawable.ic_event_bc);
        list.add(data3);

        EventData data4=new EventData();
        data4.setName("EG");
        data4.setAlpha(0.4f);
        data4.setImage(""+R.drawable.ic_event_eg);
        list.add(data4);

        EventData data5=new EventData();
        data5.setName("EN");
        data5.setAlpha(0.6f);
        data5.setImage(""+R.drawable.ic_event_en);
        list.add(data5);

        EventData data6=new EventData();
        data6.setName("UK");
        data6.setAlpha(0.8f);
        data6.setImage(""+R.drawable.ic_event_uk);
        list.add(data6);

        EventData data7=new EventData();
        data7.setName("BQ");
        data7.setAlpha(0.6f);
        data7.setImage(""+R.drawable.ic_event_bq);
        list.add(data7);

        EventData data8=new EventData();
        data8.setName("BQ");
        data8.setAlpha(0.8f);
        data8.setImage(""+R.drawable.ic_event_bq);
        list.add(data7);

        EventData data9=new EventData();
        data9.setName("BQ");
        data9.setAlpha(1.0f);
        data9.setImage(""+R.drawable.ic_event_bq);
        list.add(data9);
    }
}
