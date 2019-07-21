package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.nitrr.ecell.esummit.ecellapp.BuildConfig;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReceiver;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.AppDetails;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView ecellLogo;
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
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ecellLogo = findViewById(R.id.ecell_splash_icon);
        pref = new SharedPref();
        APICall();
        if(pref.isLoggedIn()){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
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
        if(receiver !=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
        super.onDestroy();
    }



    private void APICall() {
        Call<AppDetails> call = AppClient.getInstance().createService(APIServices.class).getAppdata();
        call.enqueue(new Callback<AppDetails>() {
            @Override
            public void onResponse(@NonNull Call<AppDetails> call, @NonNull Response<AppDetails> response) {
                if(response.isSuccessful() && getApplicationContext() != null){
                    details = response.body();
                    if(details != null){
                        checkAppVersion();
                    }
                    else{
                        try {
                            if (response.errorBody() != null) {
                                Log.e("details null====","error message is " + response.errorBody().string());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Utils.showDialog(SplashScreenActivity.this,null,false,"Something went wrong",null,"Retry", retryListener,null,null);
                }
                Log.e("unsucess API SScreen===",response.toString());
            }

            @Override
            public void onFailure(@NonNull Call<AppDetails> call, @NonNull Throwable t) {
                if(Utils.isNetworkAvailable(getApplicationContext()))
                    Utils.showDialog(SplashScreenActivity.this,null,false,getString(R.string.no_internet),null,"Retry", retryListener,"Cancel", cancelListener);
                else{
                    Utils.showLongToast(SplashScreenActivity.this,"Something went Wrong");
                }
            }
        });
    }

    private void checkAppVersion() {
        if(details.getVersion() > BuildConfig.VERSION_CODE){
            DialogInterface.OnClickListener updateListener = (dialog, which) -> {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(details.getLink())));
            };
            Utils.showDialog(SplashScreenActivity.this,null,false, getString(R.string.update_msg),
                    null, "Update",updateListener,null,null);
        }
        else{
            Intent intent;
            if(pref.isLoggedIn()){
                intent = new Intent(this,HomeActivity.class);
            }
            else
                intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
