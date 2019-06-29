package com.nitrr.ecell.esummit.ecellapp.rxsocket.events;

import com.nitrr.ecell.esummit.ecellapp.rxsocket.SocketEventType;

public class SocketEvent {

    private final SocketEventType type;

    public SocketEvent(SocketEventType type){
        this.type = type;
    }

    public SocketEventType getType(){
        return type;
    }

    @Override
    public String toString() {
        return "SocketEvent{" + "type=" + type + '}';
    }
}
