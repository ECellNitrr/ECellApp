package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.StackView;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.ESStackAdapter;
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
    private StackView stackView;
    private TextView aboutES, aboutESDetail;
    private ImageView curvedRect;
    private boolean isUp = false;
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
        stackView = findViewById(R.id.es_stack_view);
        curvedRect = findViewById(R.id.es_about_rect);
        aboutES = findViewById(R.id.es_about_text);
        aboutESDetail = findViewById(R.id.es_about_detail);

        //Bounce Animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                aboutES.startAnimation(AnimationUtils.loadAnimation(ESummitActivity.this, R.anim.slide_up));
                aboutESDetail.startAnimation(AnimationUtils.loadAnimation(ESummitActivity.this, R.anim.slide_up));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                curvedRect.startAnimation(AnimationUtils.loadAnimation(ESummitActivity.this, R.anim.bounce_up));
                aboutES.startAnimation(AnimationUtils.loadAnimation(ESummitActivity.this, R.anim.bounce_up));
                aboutESDetail.startAnimation(AnimationUtils.loadAnimation(ESummitActivity.this, R.anim.bounce_up));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        curvedRect.startAnimation(animation);

        curvedRect.setOnClickListener(view -> {
            curvedRect.setEnabled(false);
            aboutESAnimation();
        });

        responseSpeakerObjectList = new ArrayList<>();

        callAPI();
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
                        ESStackAdapter adapter = new ESStackAdapter(ESummitActivity.this, R.id.es_stack_view, responseSpeakerObjectList);
                        stackView.setAdapter(adapter);
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

    public void aboutESAnimation() {
        if(isUp) {
            ObjectAnimator.ofFloat(curvedRect, View.TRANSLATION_Y, 0f).setDuration(500).start();
            ObjectAnimator.ofFloat(aboutES, View.TRANSLATION_Y, 0f).setDuration(500).start();
            ObjectAnimator.ofFloat(aboutESDetail, View.TRANSLATION_Y, 0f).setDuration(500).start();
            new Handler().postDelayed(() -> {
                isUp = false;
                curvedRect.setEnabled(true);
            }, 500);
        } else {
            ObjectAnimator.ofFloat(curvedRect, View.TRANSLATION_Y, -1200f).setDuration(500).start();
            ObjectAnimator.ofFloat(aboutES, View.TRANSLATION_Y, -1200f).setDuration(500).start();
            ObjectAnimator.ofFloat(aboutESDetail, View.TRANSLATION_Y, -1200f).setDuration(500).start();
            new Handler().postDelayed(() -> {
                isUp = true;
                curvedRect.setEnabled(true);
            }, 500);
        }
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
