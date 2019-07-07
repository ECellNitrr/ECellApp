package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;


public class Utils {

    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static View showDialog(Context context, Integer layout, boolean canclelable, String title, String message, String posbutton, DialogInterface.OnClickListener poslistener, String negbutton, DialogInterface.OnClickListener neglistener){
        View v=null;AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        if(layout!=null){
            v=LayoutInflater.from(context).inflate(layout,null);
            dialog.setView(v) ;

        }
        dialog.setTitle(title)
                .setMessage(message)
                .setCancelable(canclelable)
                .setPositiveButton(posbutton,poslistener);
        if(negbutton!=null && neglistener!=null)
            dialog.setNegativeButton(negbutton,neglistener);
        dialog.show();
        return v;
    }
}
