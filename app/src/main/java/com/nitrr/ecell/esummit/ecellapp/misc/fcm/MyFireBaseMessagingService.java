package com.nitrr.ecell.esummit.ecellapp.misc.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.BQuizActivity;
import com.nitrr.ecell.esummit.ecellapp.activities.HomeActivity;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static String NOTIFICATION_CHANNEL_ID = "e_cell_notif_channel_id_0";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            scheduleJob();
        }

        if (remoteMessage.getNotification() != null) {

            showNotification(getBaseContext(),
                    remoteMessage.getNotification().getTitle(),
                    remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getIcon());
        }

    }

    private void scheduleJob() {
        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance(getBaseContext()).beginWith(work).enqueue();
    }


    private void showNotification(Context context, String title, String message, String icon) {
        if (context != null) {
            int notificationId = 0;

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle(title == null ? " " : title)
                    .setContentText(message == null ? " " : message)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setDefaults(NotificationCompat.DEFAULT_ALL);

            Intent intent = new Intent(context, BQuizActivity.class);
            PendingIntent pendingintent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentIntent(pendingintent);


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
