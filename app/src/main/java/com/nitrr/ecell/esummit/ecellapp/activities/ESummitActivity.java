package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.ESRVAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.ResponseSpeaker;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.ResponseSpeakerData;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;
import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReceiver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ESummitActivity extends AppCompatActivity{

    private List<ResponseSpeakerData> responseSpeakerObjectList;
    private RecyclerView speakerRV;
    private BroadcastReceiver receiver;
    private DialogInterface.OnClickListener refreshListener = (dialog, which) -> callAPI();
    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> {
        dialog.cancel();
        ESummitActivity.this.finish();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esummit);

        //Initialization
        speakerRV = findViewById(R.id.es_speaker_rv);
        responseSpeakerObjectList = new ArrayList<>();

        responseSpeakerObjectList.add(new ResponseSpeakerData("Viren Khatri", "https://cdn.pixabay.com/photo/2016/09/25/15/11/android-1693894__340.jpg",
                "WeRain", "virenk2906", 2019, "Facebook", "1234567890"));
        responseSpeakerObjectList.add(new ResponseSpeakerData("Viren Khatri", "https://cdn.pixabay.com/photo/2016/09/25/15/11/android-1693894__340.jpg",
                "WeRain", "virenk2906", 2019, "Facebook", "1234567890"));
        responseSpeakerObjectList.add(new ResponseSpeakerData("Viren Khatri", "https://cdn.pixabay.com/photo/2016/09/25/15/11/android-1693894__340.jpg",
                "WeRain", "virenk2906", 2019, "Facebook", "1234567890"));
        responseSpeakerObjectList.add(new ResponseSpeakerData("Viren Khatri", "https://cdn.pixabay.com/photo/2016/09/25/15/11/android-1693894__340.jpg",
                "WeRain", "virenk2906", 2019, "Facebook", "1234567890"));
        ESRVAdapter adapter = new ESRVAdapter(responseSpeakerObjectList, ESummitActivity.this);
        speakerRV.setAdapter(adapter);
        speakerRV.setLayoutManager(new LinearLayoutManager(this));
        speakerRV.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();

//        callAPI();
    }

    public void callAPI() {
        Call<ResponseSpeaker> call = AppClient.getInstance().createService(APIServices.class).getSpeakerList(2019);
        call.enqueue(new Callback<ResponseSpeaker>() {
            @Override
            public void onResponse(@NonNull Call<ResponseSpeaker> call, @NonNull Response<ResponseSpeaker> response) {
                if (response.isSuccessful() && getApplicationContext() != null) {
                    Log.e("response",response.toString());
                    if (response.body() == null)
                        Log.e("ES Speaker List", "response body null");
                    else {
                        ResponseSpeaker data = response.body();
                        responseSpeakerObjectList = data.getList();
                        ESRVAdapter adapter = new ESRVAdapter(responseSpeakerObjectList, ESummitActivity.this);
                        speakerRV.setAdapter(adapter);
                        speakerRV.setLayoutManager(new LinearLayoutManager(ESummitActivity.this));
                        adapter.notifyDataSetChanged();
                        Log.e("data ===========","list size is" + responseSpeakerObjectList.size());
                        Log.e("ES Speaker List", response.body().getMessage());
                    }
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("ES Speaker List", response.errorBody().string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseSpeaker> call, @NonNull Throwable t) {
                if(getApplicationContext() != null){
                    if(!Utils.isNetworkAvailable(getApplicationContext()))
                        Utils.showDialog(ESummitActivity.this,null,false,"Poor Internet Connection",getApplicationContext().getString(R.string.wasnt_able_to_load),"Retry", refreshListener,"Cancel", cancelListener);
                    else
                    {Log.e("Failure:  =","throwable is " + t);
                        Utils.showLongToast(getApplicationContext(),"Something went wrong.");
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGED");
        registerReceiver(receiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        if(receiver != null){
            unregisterReceiver(receiver);
            receiver=null;
        }
        super.onDestroy();
    }
}


//Bounce Animation
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                aboutES.startAnimation(AnimationUtils.loadAnimation(ESummitActivity.this, R.anim.slide_up));
//                aboutESDetail.startAnimation(AnimationUtils.loadAnimation(ESummitActivity.this, R.anim.slide_up));
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                curvedRect.startAnimation(AnimationUtils.loadAnimation(ESummitActivity.this, R.anim.bounce_up));
//                aboutES.startAnimation(AnimationUtils.loadAnimation(ESummitActivity.this, R.anim.bounce_up));
//                aboutESDetail.startAnimation(AnimationUtils.loadAnimation(ESummitActivity.this, R.anim.bounce_up));
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        curvedRect.startAnimation(animation);

//        curvedRect.setOnClickListener(view -> {
//            curvedRect.setEnabled(false);
//            aboutESAnimation();
//        });

//

//    public void aboutESAnimation() {
//        DisplayMetrics metrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        float height = -1 * (float)(curvedRect.getMeasuredHeight() - 0.1 * metrics.heightPixels);
//        int duration = 500;
//        if(isUp) {
//            ObjectAnimator.ofFloat(curvedRect, View.TRANSLATION_Y, 0f).setDuration(duration).start();
//            ObjectAnimator.ofFloat(aboutES, View.TRANSLATION_Y, 0f).setDuration(duration).start();
//            ObjectAnimator.ofFloat(aboutESDetail, View.TRANSLATION_Y, 0f).setDuration(duration).start();
//            new Handler().postDelayed(() -> {
//                isUp = false;
//                curvedRect.setEnabled(true);
//            }, duration);
//        } else {
//            ObjectAnimator.ofFloat(curvedRect, View.TRANSLATION_Y, height).setDuration(duration).start();
//            ObjectAnimator.ofFloat(aboutES, View.TRANSLATION_Y, height).setDuration(duration).start();
//            ObjectAnimator.ofFloat(aboutESDetail, View.TRANSLATION_Y, height).setDuration(duration).start();
//            new Handler().postDelayed(() -> {
//                isUp = true;
//                curvedRect.setEnabled(true);
//            }, duration);
//        }
//    }