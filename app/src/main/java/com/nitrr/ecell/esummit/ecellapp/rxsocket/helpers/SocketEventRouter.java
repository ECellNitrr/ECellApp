package com.nitrr.ecell.esummit.ecellapp.rxsocket.helpers;

import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEvent;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEventClosed;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEventClosing;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEventFailure;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEventMessage;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEventOpen;

import io.reactivex.FlowableEmitter;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class SocketEventRouter extends WebSocketListener {

    private final FlowableEmitter<SocketEvent> emitter;

    public SocketEventRouter(FlowableEmitter<SocketEvent> emitter){
        this.emitter = emitter;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {

        if (!emitter.isCancelled())
            emitter.onNext(new SocketEventOpen(webSocket, response));
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {

        if (!emitter.isCancelled())
            emitter.onNext(new SocketEventClosed(code, reason));
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {

        if (!emitter.isCancelled())
            emitter.onNext(new SocketEventClosing(code, reason));
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {

        if (!emitter.isCancelled())
            emitter.onNext(new SocketEventFailure(t, response));
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {

        if (!emitter.isCancelled())
            emitter.onNext(new SocketEventMessage(message));
    }
}
