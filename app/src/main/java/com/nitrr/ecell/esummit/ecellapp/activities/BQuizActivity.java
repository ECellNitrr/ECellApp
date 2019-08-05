package com.nitrr.ecell.esummit.ecellapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.BQuizQnAFragment;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.bquiz.BquizLiveCheckResponse;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BQuizActivity extends BaseActivity {

    private ProgressBar progressBar;
    private TextView proceedTextView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_bquiz;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView bQuizBG = findViewById(R.id.bquiz_bg);
        Glide.with(this)
                .load(R.drawable.bquizbg)
                .apply(new RequestOptions().centerCrop())
                .into(bQuizBG);

        proceedTextView = findViewById(R.id.bquiz_proceed);
        progressBar = findViewById(R.id.bquiz_progess_bar);

        proceedTextView.setOnClickListener(v -> {
            proceedTextView.setText("");
            progressBar.setVisibility(View.VISIBLE);

            apiCall();
        });
    }


    private void apiCall() {
        APIServices services = AppClient.getInstance().createService(APIServices.class);
        Call<BquizLiveCheckResponse> responseCall = services.isLiveRequest(new SharedPref().getAccessToken(BQuizActivity.this));

        responseCall.enqueue(new Callback<BquizLiveCheckResponse>() {
            @Override
            public void onResponse(Call<BquizLiveCheckResponse> call, Response<BquizLiveCheckResponse> response) {
                Log.e("onresponse", String.valueOf(response));
                if (response.body() != null && response.isSuccessful()) {
                    Log.e("onresponse if ", String.valueOf(response));

                    if (response.body().live) {
                        Log.e("onresponse live ", String.valueOf(response));
                        BQuizQnAFragment fragment = new BQuizQnAFragment();
                        fragment.show(getSupportFragmentManager(), "Bquiz");

                    } else{
                        Log.e("islive response","else");
                        Utils.showLongToast(BQuizActivity.this, "Bquiz isn't live right now.");
                    }


                    progressBar.setVisibility(View.GONE);
                    proceedTextView.setText(getResources().getText(R.string.proceed));
                }
            }

            @Override
            public void onFailure(Call<BquizLiveCheckResponse> call, Throwable t) {
                Log.e("onfailure", String.valueOf(t));

            }
        });
    }

    @Override
    protected void onDestroy() {
        HomeActivity.setSelected(false);
        super.onDestroy();
    }
}
