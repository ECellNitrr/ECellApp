package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;

import com.nitrr.ecell.esummit.ecellapp.R;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private AlertDialog alertDialog;

    @Override
    public void onReceive(Context context, Intent intent) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable() || mobile.isAvailable())
            if (Utils.isNetworkAvailable(context))
                online(true, context);
            else
                online(false, context);
    }

    void online(boolean isOnline, Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        if (!isOnline) {
            if (context != null) {
                View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog_network_error_layout, null);
                view.findViewById(R.id.alert_dialog_layout_button).setOnClickListener(v -> alertDialog.dismiss());

                dialog.setView(view);
                dialog.setCancelable(false);

                alertDialog = dialog.create();

                if (alertDialog.getWindow() != null)
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                alertDialog.show();
            }

        } else {
            if (alertDialog != null)
                alertDialog.dismiss();
        }
    }

}
