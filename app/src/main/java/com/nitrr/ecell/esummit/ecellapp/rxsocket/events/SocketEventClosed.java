package com.nitrr.ecell.esummit.ecellapp.rxsocket.events;

import com.nitrr.ecell.esummit.ecellapp.rxsocket.SocketEventType;


public class SocketEventClosed extends SocketEvent {

    private final int code;
    private final String reason;

    public SocketEventClosed(int code, String reason) {
        super(SocketEventType.CLOSED);

        this.code = code;
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "SocketEventClosed{" + "code=" + code + ", reason='" + reason + '\'' + '}';
    }
}
