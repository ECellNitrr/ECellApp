package com.nitrr.ecell.esummit.ecellapp.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.HomeRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.BottomSheetFragment;
import com.nitrr.ecell.esummit.ecellapp.misc.MySnapHelper;
import com.nitrr.ecell.esummit.ecellapp.models.HomeRVData;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;
    private List<HomeRVData> homeRVDataList = new ArrayList<>();
    BottomSheetFragment bottomSheet;
//    ImageButton x;
//    SnapHelper snapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomSheet = new BottomSheetFragment();
        initializeList();
        recyclerView = findViewById(R.id.home_recycler);
        adapter = new HomeRecyclerViewAdapter(this, homeRVDataList);
        setUpRV();
    }

    public void initializeList() {
        HomeRVData data4 = new HomeRVData();
        data4.setName("Sponsors");
        data4.setImage(R.drawable.ic_hand_shake);
        data4.setColor("#F2B531");
        homeRVDataList.add(data4);

        HomeRVData data3 = new HomeRVData();
        data3.setName("BQuiz");
        data3.setImage(R.drawable.ic_google);
        data3.setColor("#18A45E9");
        homeRVDataList.add(data3);

        HomeRVData data2 = new HomeRVData();
        data2.setName("Events");
        data2.setImage(R.drawable.ic_events);
        data2.setColor("#ED5958");
        homeRVDataList.add(data2);

        HomeRVData data1 = new HomeRVData();
        data1.setName("ESummit");
        data1.setImage(R.drawable.ic_esummit);
        data1.setColor("#48CFAD");
        homeRVDataList.add(data1);
    }

    public void setUpRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        recyclerView.setPadding(100,0,100,0);

        SnapHelper snapHelper = new MySnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void hamburgerClicked(View view) {
        bottomSheet.show(getSupportFragmentManager(),bottomSheet.getTag());
    }

}

//Below Code is still being reviewed it was for the changing size of the cards while scrolling.
//Error is known. Just wanna figure out how to implement the correction.
/*
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
        });*/
