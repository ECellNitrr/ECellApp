package com.nitrr.ecell.esummit.ecellapp.rxsocket.events;

import com.nitrr.ecell.esummit.ecellapp.rxsocket.SocketEventType;

public class SocketEventClosing extends SocketEvent {

    private final int code;
    private final String reason;

    public SocketEventClosing(int code, String reason) {
        super(SocketEventType.CLOSING);

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
        return "SocketEventClosing{" + "code=" + code + ", reason='" + reason + '\'' + '}';
    }
}
