package com.nitrr.ecell.esummit.ecellapp.activities;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.HomeRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.models.HomeRVData;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;
    private List<HomeRVData> homeRVDataList = new ArrayList<>();
//    SnapHelper snapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        initializeList();
        recyclerView = findViewById(R.id.home_recycler);
        recyclerView.hasFixedSize();
        adapter = new HomeRecyclerViewAdapter(this, homeRVDataList);
        setUpRV();
    }

    public void initializeList() {
        HomeRVData data = new HomeRVData();
        data.setName("Example");
        data.setImage(R.drawable.ic_google);
        homeRVDataList.add(data);
        HomeRVData data2 = new HomeRVData();
        data2.setName("Example");
        data2.setImage(R.drawable.ic_google);
        homeRVDataList.add(data2);
        HomeRVData data3 = new HomeRVData();
        data3.setName("Example");
        data3.setImage(R.drawable.ic_google);
        homeRVDataList.add(data3);
    }

    public void setUpRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        recyclerView.setPadding(100,0,100,0);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(0);
                RelativeLayout layout = viewHolder.itemView.findViewById(R.id.relLay);
                layout.animate().setDuration(150).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();

            }
        }, 100);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View v = snapHelper.findSnapView(layoutManager);
                int pos = layoutManager.getPosition(v);
                int max = layoutManager.getChildCount();

                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(pos);
                RelativeLayout layout = viewHolder.itemView.findViewById(R.id.relLay), layout2 = null, layout3 = null;
                if(pos < max) {
                    RecyclerView.ViewHolder viewHolder2 = recyclerView.findViewHolderForAdapterPosition(pos+1);
                    layout2 = viewHolder2.itemView.findViewById(R.id.relLay);
                }
                if(pos > 0) {
                    RecyclerView.ViewHolder viewHolder3 = recyclerView.findViewHolderForAdapterPosition(pos-1);
                    layout3 = viewHolder3.itemView.findViewById(R.id.relLay);
                }


                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                    layout.animate().setDuration(150).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
                    if(pos < max) {
                        layout2.animate().setDuration(150).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
                    }
                    if(pos > 0) {
                        layout3.animate().setDuration(150).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
                    }
                }
                else {
                    if(pos < max) {
                        layout2.animate().setDuration(150).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();
                    }
                    if(pos > 0) {
                        layout3.animate().setDuration(150).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();
                    }
                    layout.animate().setDuration(150).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
