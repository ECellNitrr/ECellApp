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

    private SharedPref pref;
    private String startingDate, endingDate;

    private DialogInterface.OnClickListener retryListener = (dialog, which) -> {
        appVersionAPICall();
        dialog.dismiss();
    };
    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> finish();


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

        appVersionAPICall();

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

    private void appVersionAPICall() {
        Call<AppDetails> call = AppClient.getInstance().createService(APIServices.class).getIsUpdateAvailable();
        call.enqueue(new Callback<AppDetails>() {
            @Override
            public void onResponse(@NonNull Call<AppDetails> call, @NonNull Response<AppDetails> response) {
                if(getApplicationContext() != null) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            checkAppVersion(response.body());
                        } else {
                            Log.e("SplashScreen AppUpdate", "Response Successful, empty response body");
                        }
                    } else {
                        if (response.errorBody() != null) {
                            try {
                                Log.e("SplashScreen AppUpdate", "Response Unsuccessful: " + response.errorBody().string());
                                goInsideApp();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AppDetails> call, @NonNull Throwable t) {
                Utils.showDialog(SplashScreenActivity.this,
                        null,
                        false,
                        "Internet Connection Error!",
                        null,
                        "Retry", retryListener,
                        "Cancel", cancelListener);
            }
        });
    }

    private void checkAppVersion(AppDetails details) {
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

        startingDate = details.getStartingdate();
        endingDate = details.getEndingdate();
    }

    private void goInsideApp() {
        if (pref.isLoggedIn(this)) {
            startActivity(new Intent(this, HomeActivity.class));
            Log.e("SplashScreen onSuccess", "User is Logged In");
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            Log.e("SplashScreen onSuccess", "User is not Logged In");
        }
        finish();
    }
}
