package com.nitrr.ecell.esummit.ecellapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.View;
import android.widget.ImageView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.MenuCustomAlertDialog;
import android.widget.ImageButton;

import com.nitrr.ecell.esummit.ecellapp.adapters.HomeRVAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.MySnapHelper;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.HomeRVData;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HomeRVAdapter adapter;
    private List<HomeRVData> homeRVDataList = new ArrayList<>();

    private ImageView bgCircle1, bgCircle2,  bgCircle3;

    private int distance =0,offset;
    private float displacment = 0;

    ImageButton hamburger_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.home_recycler);

        bgCircle1 = findViewById(R.id.homebg_circle1);
        bgCircle2 = findViewById(R.id.homebg_circle2);
        bgCircle3 = findViewById(R.id.homebg_circle3);

        recyclerView = findViewById(R.id.home_recycler);
        recyclerView.hasFixedSize();
        adapter = new HomeRVAdapter(this, homeRVDataList);
        setUpRV();

        initializeList("ESummit", R.drawable.ic_esummit, this.getString(R.string.color_esummit), v -> {
            Intent intent = new Intent(HomeActivity.this, ESummitActivity.class);
            startActivity(intent);
        });

        initializeList("Events", R.drawable.ic_events, this.getString(R.string.color_events), v -> {
            Intent intent = new Intent(HomeActivity.this, EventActivity.class);
            startActivity(intent);
        });

        initializeList("BQuiz", R.drawable.ic_event_bq, this.getString(R.string.color_bquiz), v -> {
            AlertDialog dialog = null;
            dialog = Utils.showDialog(this, null, true, "Sorry for the Inconvenience",
                    "BQuiz will be online soon, Please checkout later",
                    null, null, "Cancel", (dialogInterface, i) -> {
                dialogInterface.dismiss();
                    });
        });

        initializeList("Sponsors", R.drawable.ic_hand_shake, this.getString(R.string.color_spons), v -> {
            Intent intent = new Intent(HomeActivity.this, SponsorsActivity.class);
            startActivity(intent);
        });

        hamburger_button = findViewById(R.id.hamburgerButton);
        hamburger_button.setOnClickListener((View view) -> MenuCustomAlertDialog.getInstance().with(this).build());
        recyclerView = findViewById(R.id.home_recycler);
        recyclerView.hasFixedSize();
        setUpRV();
    }


    public void setUpRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setPadding(100, 0, 100, 0);

        SnapHelper snapHelper = new MySnapHelper();
        if(recyclerView.getOnFlingListener()==null)
            snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                distance = recyclerView.computeHorizontalScrollRange();
                offset=recyclerView.computeHorizontalScrollOffset();
                if (offset < distance/4) {
                    displacment = (float) offset / 885;
                    bgCircle1.setColorFilter(color(85,216,183,252,110,81, displacment));
                    bgCircle2.setColorFilter(color(85,216,183,252,110,81, displacment));
                    bgCircle3.setColorFilter(color(85,216,183,252,110,81, displacment));

                } else if (offset < distance/2) {
                    displacment = (float) (offset - (distance/4)) / 885;
                    bgCircle1.setColorFilter(color(252,110,81,88,180,225, displacment));
                    bgCircle2.setColorFilter(color(252,110,81,88,180,225, displacment));
                    bgCircle3.setColorFilter(color(252,110,81,88,180,225, displacment));
                } else if (offset < (distance*3/4)) {
                    displacment = (float) (offset - (distance/2)) / 885;
                    bgCircle1.setColorFilter(color(88,180,225,249,207,109, displacment));
                    bgCircle2.setColorFilter(color(88,180,225,249,207,109, displacment));
                    bgCircle3.setColorFilter(color(88,180,225,249,207,109, displacment));
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    int color(int IR,int IG,int IB,int FR,int FG,int FB,float pos){
        return Color.rgb(colorValue(IR,FR,pos),colorValue(IG,FG,pos),colorValue(IB,FB,pos));
    }

    int colorValue(int Initial,int Final,float pos){
        return (int)(Final*pos+Initial*(1-pos));
    }

    public void initializeList(String name, int cardImage, String color,View.OnClickListener listener) {
        HomeRVData data = new HomeRVData(name, color, cardImage,listener);
        homeRVDataList.add(data);
    }
}