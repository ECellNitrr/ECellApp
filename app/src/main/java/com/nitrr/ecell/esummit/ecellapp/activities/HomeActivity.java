package com.nitrr.ecell.esummit.ecellapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.HomeRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.MenuCustomAlertDialog;
import com.nitrr.ecell.esummit.ecellapp.misc.MySnapHelper;
import com.nitrr.ecell.esummit.ecellapp.models.HomeRVData;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;
    private List<HomeRVData> homeRVDataList = new ArrayList<>();

    private ImageView esummitbg;
    private ImageView eventbg;
    private ImageView bquizbg;
    private ImageView sponsbg;

    private int rvpositionx = 0;

    private float scrollpos1 = 0;
    private float scrollpos2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeList();

        recyclerView = findViewById(R.id.home_recycler);

        esummitbg = findViewById(R.id.home_bg1);
        eventbg = findViewById(R.id.home_bg2);
        bquizbg = findViewById(R.id.home_bg3);
        sponsbg = findViewById(R.id.home_bg4);

        adapter = new HomeRecyclerViewAdapter(this, homeRVDataList);

        setUpRV();
    }

    public void initializeList() {
        HomeRVData eSummit = new HomeRVData();

        eSummit.setName(getResources().getString(R.string.text_esummit));
        eSummit.setImage(R.drawable.ic_esummit);
        eSummit.setColor(this.getString(R.string.color_esummit));

        homeRVDataList.add(eSummit);

        HomeRVData events = new HomeRVData();

        events.setName(getResources().getString(R.string.text_events));
        events.setImage(R.drawable.ic_events);
        events.setColor(getResources().getString(R.string.color_events));

        homeRVDataList.add(events);

        HomeRVData bQuiz = new HomeRVData();

        bQuiz.setName(getResources().getString(R.string.text_bquiz));
        bQuiz.setImage(R.drawable.ic_google);
        bQuiz.setColor(getResources().getString(R.string.color_bquiz));

        homeRVDataList.add(bQuiz);

        HomeRVData sponsors = new HomeRVData();

        sponsors.setName(getResources().getString(R.string.text_spons));
        sponsors.setImage(R.drawable.ic_hand_shake);
        sponsors.setColor(getResources().getString(R.string.color_spons));

        homeRVDataList.add(sponsors);
    }

    public void setUpRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        recyclerView.setPadding(100, 0, 100, 0);

        SnapHelper snapHelper = new MySnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(0);
                        RelativeLayout layout = viewHolder.itemView.findViewById(R.id.relLay);
                        layout.animate().setDuration(150).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();

                    }
                }, 100);
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
                }*/
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                Log.w("positionX ", "value of revposX = " + rvpositionx);
                rvpositionx += dx;
                if (rvpositionx < 885) {
                    scrollpos1 = (float) rvpositionx / 885;
                    scrollpos2 = 1 - scrollpos1;
                    esummitbg.setAlpha(scrollpos2);
                    eventbg.setAlpha(scrollpos1);
                    bquizbg.setAlpha(0f);
                    sponsbg.setAlpha(0f);

                } else if (rvpositionx < 1770) {
                    scrollpos1 = (float) (rvpositionx - 885) / 885;
                    scrollpos2 = 1 - scrollpos1;
                    eventbg.setAlpha(scrollpos2);
                    bquizbg.setAlpha(scrollpos1);
                    esummitbg.setAlpha(0f);
                    sponsbg.setAlpha(0f);
                } else if (rvpositionx < 2655) {
                    scrollpos1 = (float) (rvpositionx - 1770) / 885;
                    scrollpos2 = 1 - scrollpos1;
                    bquizbg.setAlpha(scrollpos2);
                    sponsbg.setAlpha(scrollpos1);
                    eventbg.setAlpha(0f);
                    esummitbg.setAlpha(0f);
                }

                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void hamburgerClicked(View view) {
        MenuCustomAlertDialog.getInstance().with(this).build();
    }

}