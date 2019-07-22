package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.annotation.NonNull;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.nitrr.ecell.esummit.ecellapp.BuildConfig;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.AppDetails;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends BaseActivity {

    private AppDetails details;
    private SharedPref pref;

    private DialogInterface.OnClickListener retryListener = (dialog, which) -> {
        APICall();
        dialog.dismiss();
    };

    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> {
        this.finish();
        dialog.dismiss();
    };

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splashscreen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        pref = new SharedPref();

        APICall();
        
        if(pref.isLoggedIn(this)) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

        Animation animation = new AlphaAnimation(1.0f, 0.5f);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);

        findViewById(R.id.ecell_splash_icon).setAnimation(animation);

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (date.equals("2019-08-31") || date.equals("2019-10-01"))
            ((ImageView) findViewById(R.id.ecell_splash_icon)).setImageResource(R.drawable.ic_esummit);
    }

    private void APICall() {
        Call<AppDetails> call = AppClient.getInstance().createService(APIServices.class).getAppdata();
        call.enqueue(new Callback<AppDetails>() {
            @Override
            public void onResponse(@NonNull Call<AppDetails> call, @NonNull Response<AppDetails> response) {
                if (response.isSuccessful() && getApplicationContext() != null) {
                    details = response.body();
                    if (details != null) {
                        checkAppVersion();
                    } else {

                        try {
                            if (response.errorBody() != null) {
                                Log.e("details null====", "error message is " + response.errorBody().string());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    Utils.showDialog(SplashScreenActivity.this,
                            null,
                            false,
                            "Something's wrong!",
                            null,
                            "Retry", retryListener,
                            null, null);
                }

                Log.e("unsucess API Screen===", response.toString());
            }

            @Override
            public void onFailure(@NonNull Call<AppDetails> call, @NonNull Throwable t) {
                if (Utils.isNetworkAvailable(getApplicationContext()))
                    Utils.showDialog(SplashScreenActivity.this, null, false, getString(R.string.no_internet), null, "Retry", retryListener, "Cancel", cancelListener);
                else {
                    Utils.showLongToast(SplashScreenActivity.this, "Something went Wrong");
                }
            }
        });
    }

    private void checkAppVersion() {
        if (details.getVersion() > BuildConfig.VERSION_CODE) {

            DialogInterface.OnClickListener updateListener = (dialog, which) -> {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(details.getLink())));
                finish();
            };


            Utils.showDialog(SplashScreenActivity.this, null, false,
                    getString(R.string.update_msg), null,
                    "Update", updateListener, null, null);
        } else
            goInsideApp();
    }

    private void goInsideApp() {
        Intent intent;

        if (pref.isLoggedIn(this)) {
            intent = new Intent(this, HomeActivity.class);
        } else
            intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
        finish();
    }
}
