package com.nitrr.ecell.esummit.ecellapp.misc.fcm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            scheduleJob();
        }

        if (remoteMessage.getNotification() != null) {

            Utils.showNotification(getBaseContext(),
                    remoteMessage.getNotification().getTitle(),
                    remoteMessage.getNotification().getBody(), true);
        }

    }

    private void scheduleJob() {
        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance(getBaseContext()).beginWith(work).enqueue();
    }
}
