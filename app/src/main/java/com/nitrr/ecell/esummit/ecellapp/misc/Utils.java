package com.nitrr.ecell.esummit.ecellapp.misc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


public class Utils {

    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
