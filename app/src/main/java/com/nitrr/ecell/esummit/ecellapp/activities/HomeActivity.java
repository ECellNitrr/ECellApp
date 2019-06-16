package com.nitrr.ecell.esummit.ecellapp.activities;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.HomeRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.BottomSheetFragment;
import com.nitrr.ecell.esummit.ecellapp.misc.MySnapHelper;
import com.nitrr.ecell.esummit.ecellapp.models.HomeRVData;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    int test = 0;
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;
    private List<HomeRVData> homeRVDataList = new ArrayList<>();
    private BottomSheetFragment bottomSheet;
    private ImageView esummitbg;
    private ImageView eventbg;
    private ImageView bquizbg;
    private ImageView sponsbg;
    private int rvpositionx = 0;
    private float scrollpos1 = 0;
    private float scrollpos2 = 0;
//    ImageButton x;
//    SnapHelper snapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomSheet = new BottomSheetFragment();
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
        HomeRVData data1 = new HomeRVData();
        data1.setName("ESummit");
        data1.setImage(R.drawable.ic_esummit);
        data1.setColor("#48CFAD");
        homeRVDataList.add(data1);

        HomeRVData data2 = new HomeRVData();
        data2.setName("Events");
        data2.setImage(R.drawable.ic_events);
        data2.setColor("#ED5958");
        homeRVDataList.add(data2);

        HomeRVData data3 = new HomeRVData();
        data3.setName("BQuiz");
        data3.setImage(R.drawable.ic_google);
        data3.setColor("#18A45E9");
        homeRVDataList.add(data3);

        HomeRVData data4 = new HomeRVData();
        data4.setName("Sponsors");
        data4.setImage(R.drawable.ic_hand_shake);
        data4.setColor("#F2B531");
        homeRVDataList.add(data4);
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
        bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
    }

}