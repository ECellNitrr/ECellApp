package com.nitrr.ecell.esummit.ecellapp.misc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


public class Utils {

    public void showToast(Context context, String message){
        android.os.Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
