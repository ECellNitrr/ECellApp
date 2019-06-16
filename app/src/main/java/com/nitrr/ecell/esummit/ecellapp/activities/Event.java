package com.nitrr.ecell.esummit.ecellapp.activities;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.EventRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.models.EventRVData;

import java.util.ArrayList;
import java.util.List;

public class Event extends AppCompatActivity {
    private List<EventRVData> list= new ArrayList<EventRVData>();
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
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.set(20,0,0,0);
            super.getItemOffsets(outRect, view, parent, state);
            }
        });
        adapter = new EventRecyclerViewAdapter(this,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new SpacesItem);
    }

    private void initializeList() {
        EventRVData data1=new EventRVData();
        data1.setName("CR");
        data1.setAlpha(0.2f);
        data1.setImage(R.drawable.ic_event_cr);
        list.add(data1);

        EventRVData data2=new EventRVData();
        data2.setName("BQ");
        data2.setAlpha(0.4f);
        data2.setImage(R.drawable.ic_event_bq);
        list.add(data2);

        EventRVData data3=new EventRVData();
        data3.setName("BC");
        data3.setAlpha(0.6f);
        data3.setImage(R.drawable.ic_event_bc);
        list.add(data3);

        EventRVData data4=new EventRVData();
        data4.setName("EG");
        data4.setAlpha(0.4f);
        data4.setImage(R.drawable.ic_event_eg);
        list.add(data4);

        EventRVData data5=new EventRVData();
        data5.setName("EN");
        data5.setAlpha(0.6f);
        data5.setImage(R.drawable.ic_event_en);
        list.add(data5);

        EventRVData data6=new EventRVData();
        data6.setName("UK");
        data6.setAlpha(0.8f);
        data6.setImage(R.drawable.ic_event_uk);
        list.add(data6);

        EventRVData data7=new EventRVData();
        data7.setName("BQ");
        data7.setAlpha(0.6f);
        data7.setImage(R.drawable.ic_event_bq);
        list.add(data7);

        EventRVData data8=new EventRVData();
        data8.setName("BQ");
        data8.setAlpha(0.8f);
        data8.setImage(R.drawable.ic_event_bq);
        list.add(data7);

        EventRVData data9=new EventRVData();
        data9.setName("BQ");
        data9.setAlpha(1.0f);
        data9.setImage(R.drawable.ic_event_bq);
        list.add(data9);
    }
}
