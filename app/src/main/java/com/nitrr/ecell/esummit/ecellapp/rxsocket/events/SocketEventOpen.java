package com.nitrr.ecell.esummit.ecellapp.rxsocket.events;

import com.nitrr.ecell.esummit.ecellapp.rxsocket.SocketEventType;

import okhttp3.Response;
import okhttp3.WebSocket;

public class SocketEventOpen extends SocketEvent {

    private WebSocket webSocket;
    private Response response;

    public SocketEventOpen(WebSocket webSocket, Response response) {
        super(SocketEventType.OPEN);

        this.response = response;
        this.webSocket = webSocket;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "SocketEventOpen{" + "webSocket=" + webSocket + ", response=" + response + '}';
    }
}
