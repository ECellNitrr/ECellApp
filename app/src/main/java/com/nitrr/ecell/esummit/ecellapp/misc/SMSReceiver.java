package com.nitrr.ecell.esummit.ecellapp.misc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

    private static SMSListener smsListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] PDUS = (Object[]) data.get("pdus");

        for (int i = 0; i < (PDUS != null ? PDUS.length : 0); i++) {
            SmsMessage smsMessage;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
                smsMessage = SmsMessage.createFromPdu((byte[]) PDUS[i], data.getString("format"));
            else
                smsMessage = SmsMessage.createFromPdu((byte[]) PDUS[i]);

            if(smsMessage.getDisplayOriginatingAddress().endsWith("SUMMIT"))
                smsListener.onReceived(smsMessage.getMessageBody().replaceAll("[^0-9]", ""));

            this.abortBroadcast();
        }
    }

    public static void bindSMSListener(SMSListener smsListener) {
        SMSReceiver.smsListener = smsListener;
    }

    @FunctionalInterface
    public interface SMSListener {
        void onReceived(String message);
    }
}
