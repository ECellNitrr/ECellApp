package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.HomeActivity;

import java.util.Objects;

import retrofit2.http.HEAD;


public class Utils {

    private static AlertDialog dialog;

    public static void showLongToast(Context context, String message) {
        if (context != null)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String message) {
        if (context != null)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();

                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }

            } else {
                final Network n = cm.getActiveNetwork();

                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);

                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }

        return false;
    }

    public static AlertDialog showDialog(Context context, Integer layout, boolean cancelable, String title,
                                         String message, String posButton, DialogInterface.OnClickListener posListener,
                                         String negButton, DialogInterface.OnClickListener negListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (!((Activity) context).isFinishing()) {

            if (layout != null) {
                View v = LayoutInflater.from(context).inflate(layout, null);
                builder.setView(v);
            }

            builder.setTitle(title)
                    .setMessage(message)
                    .setCancelable(cancelable);

            if (posButton != null)
                builder.setPositiveButton(posButton, posListener);

            if (negButton != null && negListener != null)
                builder.setNegativeButton(negButton, negListener);
            builder.create().show();
        }

        return dialog;
    }

    public static AlertDialog showProgressBar(Context context, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_progress_layout, null);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setLayout(200, 200);

        Animation animation = new AlphaAnimation(1.0f, 0.5f);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);

        dialog.setView(view);

        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ((TextView) view.findViewById(R.id.alert_progress_dialog_text)).setText(text);

        view.findViewById(R.id.alert_progress_dialog_image).setAnimation(animation);

        dialog.show();

        return dialog;
    }
}




