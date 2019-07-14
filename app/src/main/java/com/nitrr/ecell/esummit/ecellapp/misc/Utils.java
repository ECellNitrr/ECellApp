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
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
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
import androidx.core.app.NotificationCompat;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.HomeActivity;


public class Utils {

    public static void showLongToast(Context context, String message){
        if(context!=null)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String message){
        if(context!=null)
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
        if(context!=null){
            if(layout!=null){
                v=LayoutInflater.from(context).inflate(layout,null);
                dialog.setView(v);
            }
            dialog.setTitle(title)
                    .setMessage(message)
                    .setCancelable(canclelable);
            if(posbutton!=null)
                    dialog.setPositiveButton(posbutton,poslistener);
            if(negbutton!=null && neglistener!=null)
                dialog.setNegativeButton(negbutton,neglistener);
            dialog.show();
        }
        return v;
    }

    public static void showNotification(Context context,@NonNull String title,@NonNull String message,Boolean isintent){
        if(context!=null) {
            int notificationId = 0;
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_close)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_close))
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setDefaults(NotificationCompat.DEFAULT_ALL);
            if(isintent){
                Intent intent = new Intent(context, HomeActivity.class);
                PendingIntent pendingintent = PendingIntent.getActivity(context,0,intent,0);
                builder.setContentIntent(pendingintent);
            }
            Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            builder.setSound(path);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelid = "YOUR_CHANNEL_ID";
                NotificationChannel channel = new NotificationChannel(channelid, "Channel", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelid);
            }
            notificationManager.notify(notificationId, builder.build());
        }
    }
}




