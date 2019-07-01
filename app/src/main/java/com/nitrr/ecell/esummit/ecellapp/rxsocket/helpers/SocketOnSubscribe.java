package com.nitrr.ecell.esummit.ecellapp.rxsocket.helpers;

import androidx.annotation.NonNull;

import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SocketOnSubscribe implements FlowableOnSubscribe<SocketEvent> {

    private final OkHttpClient client;
    private final Request request;


    public SocketOnSubscribe(@NonNull String URL) {
        client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();

        request = new Request.Builder()
                .url(URL)
                .build();
    }

    public SocketOnSubscribe(@NonNull OkHttpClient client, @NonNull String URL) {
        this.client = client;

        request = new Request.Builder()
                .url(URL)
                .build();
    }

    @Override
    public void subscribe(FlowableEmitter<SocketEvent> emitter) throws Exception {
        client.newWebSocket(request, new SocketEventRouter(emitter));
    }
}
