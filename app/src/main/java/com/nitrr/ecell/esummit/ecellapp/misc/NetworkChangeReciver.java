package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;


public class NetworkChangeReciver extends BroadcastReceiver {

    AlertDialog alertDialog;

    @Override
    public void onReceive(Context context, Intent intent) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(wifi.isAvailable() || mobile.isAvailable())
            if(Utils.isNetworkAvailable(context))
                online(true,context);
        else
            online(false,context);
    }

    void online(boolean isonline,Context context){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        if(!isonline){
            if(context!=null){
                alertDialog = dialog.create();
                dialog.setTitle("No Internet Connection")
                        .setMessage("Please Connect to internet")
                        .setPositiveButton("",null)
                        .setCancelable(false);
                alertDialog =dialog.show();
            }
        }
        else{
            if(alertDialog!=null)
                alertDialog.dismiss();
        }
    }

}
