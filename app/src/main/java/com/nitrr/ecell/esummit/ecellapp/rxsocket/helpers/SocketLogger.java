package com.nitrr.ecell.esummit.ecellapp.rxsocket.helpers;

import android.util.Log;

import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEvent;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SocketLogger implements Subscriber<SocketEvent> {

    private final String TAG;

    public SocketLogger(String tag){
        TAG = tag + ": ";
    }

    @Override
    public void onSubscribe(Subscription s) {
        Log.d(TAG, "Subscribe.");
    }

    @Override
    public void onNext(SocketEvent socketEvent) {
        Log.d(TAG, "Next.");
        Log.d(TAG, socketEvent.toString());
    }

    @Override
    public void onError(Throwable t) {
        Log.e(TAG, t.getMessage());
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "Complete.");
    }
}
