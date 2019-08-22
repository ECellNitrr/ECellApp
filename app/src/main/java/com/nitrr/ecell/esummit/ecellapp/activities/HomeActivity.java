package com.nitrr.ecell.esummit.ecellapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.HomeRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.CustomHamburgerDialog;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.HomeRVData;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private static Boolean selected = false;
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;
    private List<HomeRVData> homeRVDataList = new ArrayList<>();
    private ImageView bgCircle1, bgCircle2, bgCircle3;
    private int distance = 0, offset;
    private float displacement = 0;

    public static void setSelected(Boolean selected) {
        HomeActivity.selected = selected;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.home_recycler);
        bgCircle1 = findViewById(R.id.homebg_circle1);
        bgCircle2 = findViewById(R.id.homebg_circle2);
        bgCircle3 = findViewById(R.id.homebg_circle3);

        recyclerView = findViewById(R.id.home_recycler);
        recyclerView.hasFixedSize();
        adapter = new HomeRecyclerViewAdapter(this, homeRVDataList);
        initializeList("E - Summit", R.drawable.ic_esummit, this.getString(R.string.color_esummit), v -> {
            if (!selected) {
                selected = true;
                Intent intent = new Intent(HomeActivity.this, ESummitActivity.class);
                startActivity(intent);
            }
        });

        initializeList("Events", R.drawable.ic_events, this.getString(R.string.color_events), v -> {
            if (!selected) {
                selected = true;
                Intent intent = new Intent(HomeActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });

        initializeList("BQuiz", R.drawable.ic_event_bq, this.getString(R.string.color_bquiz), v -> {
            if (!selected) {
                Intent intent = new Intent(HomeActivity.this, BQuizActivity.class);
                startActivity(intent);
                selected = true;
            }
        });

        initializeList("Sponsors", R.drawable.ic_hand_shake, this.getString(R.string.color_spons), v -> {
            if (!selected) {
                selected = true;
                Intent intent = new Intent(HomeActivity.this, SponsorsActivity.class);
                startActivity(intent);
            }
        });

        ImageButton hamburger_button = findViewById(R.id.hamburgerButton);
        hamburger_button.setOnClickListener((View view) -> new CustomHamburgerDialog().with(HomeActivity.this).build());
        recyclerView = findViewById(R.id.home_recycler);
        recyclerView.hasFixedSize();

        setUpRV();
    }

    public void setUpRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setPadding(100, 0, 100, 0);

        SnapHelper snapHelper = new PagerSnapHelper();
        if (recyclerView.getOnFlingListener() == null)
            snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                distance = recyclerView.computeHorizontalScrollRange();
                offset = recyclerView.computeHorizontalScrollOffset();
                if (offset < distance / 4) {
                    displacement = (float) offset / (distance / 4);
                    setColor(98, 91, 93, 241, 104, 133, displacement);

                } else if (offset < distance / 2) {
                    displacement = (float) (offset - (distance / 4)) / (distance / 4);
                    setColor(241, 104, 133, 146, 82, 130, displacement);
                } else if (offset < (distance * 3 / 4)) {
                    displacement = (float) (offset - (distance / 2)) / (distance / 4);
                    setColor(146, 82, 130, 245, 173, 76, displacement);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    void setColor(int IR, int IG, int IB, int FR, int FG, int FB, float pos) {
        bgCircle1.setColorFilter(color(IR, IG, IB, FR, FG, FB, pos));
        bgCircle2.setColorFilter(color(IR, IG, IB, FR, FG, FB, pos));
        bgCircle3.setColorFilter(color(IR, IG, IB, FR, FG, FB, pos));
    }

    int color(int IR, int IG, int IB, int FR, int FG, int FB, float pos) {
        return Color.rgb(colorValue(IR, FR, pos), colorValue(IG, FG, pos), colorValue(IB, FB, pos));
    }

    int colorValue(int Initial, int Final, float pos) {
        return (int) (Final * pos + Initial * (1 - pos));
    }

    public void initializeList(String name, int cardImage, String color, View.OnClickListener listener) {
        HomeRVData data = new HomeRVData(name, color, cardImage, listener);
        homeRVDataList.add(data);
    }

    @Override
    public void onBackPressed() {
        if(new SharedPref().getIsVerifying(this))
            Utils.showLongToast(this, "Please verify mobile number to proceed.");
        else
            super.onBackPressed();
    }
}

//============================== VERIFY OTP API ============================

//    private SharedPref pref = new SharedPref();
//
//    private DialogInterface.OnClickListener verifyOTPListener = (dialog, which) -> {
//        pref.setGreeted(HomeActivity.this, true);
//        OTPDialogFragment fragment = new OTPDialogFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("prevfrag", "Home Activity");
//        fragment.setArguments(bundle);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.home_parent_layout, fragment)
//                .addToBackStack(null)
//                .commit();
//    };
//    private DialogInterface.OnClickListener changeNumberListener = (dialog, which) -> {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.home_parent_layout, new ChangeNumberFragment())
//                .addToBackStack(null)
//                .commit();
//    };

//    private DialogInterface.OnClickListener closeListener = (dialog, which) -> finish();
//    private DialogInterface.OnClickListener retryListener = (dialog, which) -> isVerifiedAPICall();

//    isVerifiedAPICall();

//    void isVerifiedAPICall() {
//        AlertDialog dialog = Utils.showProgressBar(this, "Please wait for a moment");
//        Call<UserVerifiedModel> call = AppClient.getInstance().createService(APIServices.class)
//                .isVerified(getString(R.string.app_access_token), pref.getAccessToken(this));
//        call.enqueue(new Callback<UserVerifiedModel>() {
//            @Override
//            public void onResponse(@NonNull Call<UserVerifiedModel> call, @NonNull Response<UserVerifiedModel> response) {
//                dialog.dismiss();
//                if (getApplicationContext() != null) {
//                    if (response.isSuccessful()) {
//                        if (response.body() != null) {
//                            pref.setMobileVerified(HomeActivity.this, response.body().getUserIsVerified());
//                            Log.e("HomeActivity isVerified", "Response Successful! Response:"
//                                    + response.body().getUserIsVerified());
//                        } else
//                            Log.e("HomeActivity isVerified", "Response Successful: Response Body NULL");
//                    } else {
//                        if (response.errorBody() != null) {
//                            try {
//                                Log.e("HomeActivity isVerified", "Response Unsuccessful: " + response.errorBody().string());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<UserVerifiedModel> call, @NonNull Throwable t) {
//                if (Utils.isNetworkAvailable(getApplicationContext()))
//                    Utils.showDialog(getApplicationContext(), null, false,
//                            "No Internet Connection", "Please connect to internet and try again",
//                            "Retry", retryListener,
//                            "Close App", closeListener);
//            }
//        });
//    }
