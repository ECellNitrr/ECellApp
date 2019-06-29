package com.nitrr.ecell.esummit.ecellapp.rxsocket.events;

import com.nitrr.ecell.esummit.ecellapp.rxsocket.SocketEventType;

import okhttp3.Response;

public class SocketEventFailure extends SocketEvent {

    private final Throwable throwable;
    private final Response response;

    public SocketEventFailure(Throwable throwable, Response response) {
        super(SocketEventType.FAILURE);

        this.response = response;
        this.throwable = throwable;
    }

    public Throwable getException() {
        return throwable;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "SocketEventFailure{" + "exception=" + throwable.getMessage() + ", response=" + response + '}';
    }
}
