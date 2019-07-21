package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.HomeActivity;


public class Utils {

    private static AlertDialog dialog;
    private static String NOTIFICATION_CHANNEL_ID = "e_cell_notif_channel_id_0";

    public static void showLongToast(Context context, String message) {
        if (context != null)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String message) {
        if (context != null)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return true;
    }

    public static AlertDialog showDialog(Context context, Integer layout, boolean cancelable, String title, String message, String posButton, DialogInterface.OnClickListener posListener, String negButton, DialogInterface.OnClickListener negListener) {
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

            dialog = builder.create();

            if (dialog.getWindow() != null)
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();
        }

        return dialog;
    }

    public static void showNotification(Context context, @NonNull String title, @NonNull String message, Boolean allowIntent) {
        if (context != null) {
            int notificationId = 0;

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_ecell)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_close))
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setDefaults(NotificationCompat.DEFAULT_ALL);

            if (allowIntent) {
                Intent intent = new Intent(context, HomeActivity.class);
                PendingIntent pendingintent = PendingIntent.getActivity(context, 0, intent, 0);
                builder.setContentIntent(pendingintent);
            }

            Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            builder.setSound(path);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "E-Cell Notifications", NotificationManager.IMPORTANCE_DEFAULT);

                notificationManager.createNotificationChannel(channel);
                builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            }

            notificationManager.notify(notificationId, builder.build());
        }
    }
}




