package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nitrr.ecell.esummit.ecellapp.R;


public class Utils {

    private static Activity activity;

    public Utils(Activity activity) {
        if(activity == null)
            this.activity = activity;
    }

    public static void showLongToast(Context context, String message){

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkAvailable(Context context) {
        if(context!=null){
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return true;
    }

    public static View showDialog(Context context, Integer layout, boolean canclelable, String title, String message, String posbutton, DialogInterface.OnClickListener poslistener, String negbutton, DialogInterface.OnClickListener neglistener){
        View v = null;
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        if(!((Activity)context).isFinishing()){
            if(layout!=null){
                v=LayoutInflater.from(context).inflate(layout,null);
                dialog.setView(v);
            }
            dialog.setTitle(title)
                    .setMessage(message)
                    .setCancelable(canclelable)
                    .setPositiveButton(posbutton,poslistener);
            if(negbutton!=null && neglistener!=null)
                dialog.setNegativeButton(negbutton,neglistener);
            dialog.show();
        }
        return v;
    }

    public static String getAccessToken() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getString("access_token", null);
    }
}




