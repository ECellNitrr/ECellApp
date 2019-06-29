package com.nitrr.ecell.esummit.ecellapp.activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.MenuCustomAlertDialog;
import android.widget.ImageButton;

import com.nitrr.ecell.esummit.ecellapp.adapters.HomeRVAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.MySnapHelper;
import com.nitrr.ecell.esummit.ecellapp.models.HomeRVData;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HomeRVAdapter adapter;
    private List<HomeRVData> homeRVDataList = new ArrayList<>();

    private ImageView esummitBG;
    private ImageView eventBG;
    private ImageView bQuizBG;
    private ImageView sponsBG;

    private int rvpositionx = 0;

    private float scrollpos1 = 0;
    private float scrollpos2 = 0;
    ImageButton hamburger_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.home_recycler);

        esummitBG = findViewById(R.id.home_bg1);
        eventBG = findViewById(R.id.home_bg2);
        bQuizBG = findViewById(R.id.home_bg3);
        sponsBG = findViewById(R.id.home_bg4);

        adapter = new HomeRVAdapter(this, homeRVDataList);
        initializeList("BQuiz", R.drawable.ic_google, this.getString(R.string.color_bquiz));
        initializeList("Events", R.drawable.ic_events, this.getString(R.string.color_events));
        initializeList("ESummit", R.drawable.ic_esummit, this.getString(R.string.color_esummit));
        initializeList("Sponsors", R.drawable.ic_hand_shake, this.getString(R.string.color_spons));

        hamburger_button = findViewById(R.id.hamburgerButton);
        hamburger_button.setOnClickListener((View view) -> MenuCustomAlertDialog.getInstance().with(this).build());

        recyclerView = findViewById(R.id.home_recycler);
        recyclerView.hasFixedSize();
        setUpRV();
    }


    public void initializeList(String name, int cardImage, String color) {
        HomeRVData data = new HomeRVData(name, cardImage, color);
        homeRVDataList.add(data);
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
                    esummitBG.setAlpha(scrollpos2);
                    eventBG.setAlpha(scrollpos1);
                    bQuizBG.setAlpha(0f);
                    sponsBG.setAlpha(0f);

                } else if (rvpositionx < 1770) {
                    scrollpos1 = (float) (rvpositionx - 885) / 885;
                    scrollpos2 = 1 - scrollpos1;
                    eventBG.setAlpha(scrollpos2);
                    bQuizBG.setAlpha(scrollpos1);
                    esummitBG.setAlpha(0f);
                    sponsBG.setAlpha(0f);
                } else if (rvpositionx < 2655) {
                    scrollpos1 = (float) (rvpositionx - 1770) / 885;
                    scrollpos2 = 1 - scrollpos1;
                    bQuizBG.setAlpha(scrollpos2);
                    sponsBG.setAlpha(scrollpos1);
                    eventBG.setAlpha(0f);
                    esummitBG.setAlpha(0f);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }



}

//Below Code is still being reviewed it was for the changing size of the cards while scrolling.
//Error is known. Just wanna figure out how to implement the correction.

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(0);
//                RelativeLayout layout = viewHolder.itemView.findViewById(R.id.relLay);
//                layout.animate().setDuration(150).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
//
//            }
//        }, 100);
//
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                View v = snapHelper.findSnapView(layoutManager);
//                int pos = layoutManager.getPosition(v);
//                int max = layoutManager.getChildCount();
//
//                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(pos);
//                RelativeLayout layout = viewHolder.itemView.findViewById(R.id.relLay), layout2 = null, layout3 = null;
//                if(pos < max) {
//                    RecyclerView.ViewHolder viewHolder2 = recyclerView.findViewHolderForAdapterPosition(pos+1);
//                    layout2 = viewHolder2.itemView.findViewById(R.id.relLay);
//                }
//                if(pos > 0) {
//                    RecyclerView.ViewHolder viewHolder3 = recyclerView.findViewHolderForAdapterPosition(pos-1);
//                    layout3 = viewHolder3.itemView.findViewById(R.id.relLay);
//                }
//
//
//                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    layout.animate().setDuration(150).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
//                    if(pos < max) {
//                        layout2.animate().setDuration(150).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
//                    }
//                    if(pos > 0) {
//                        layout3.animate().setDuration(150).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
//                    }
//                }
//                else {
//                    if(pos < max) {
//                        layout2.animate().setDuration(150).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();
//                    }
//                    if(pos > 0) {
//                        layout3.animate().setDuration(150).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();
//                    }
//                    layout.animate().setDuration(150).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();
//                }
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
