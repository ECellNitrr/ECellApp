package com.nitrr.ecell.esummit.ecellapp.rxsocket.events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nitrr.ecell.esummit.ecellapp.rxsocket.SocketEventType;

import okio.ByteString;

public class SocketEventMessage extends SocketEvent {

    private final String message;
    private final ByteString byteString;

    public SocketEventMessage(@NonNull String message) {
        super(SocketEventType.MESSAGE);

        this.message = message;
        this.byteString = null;
    }

    public SocketEventMessage(@NonNull ByteString bytes){
        super(SocketEventType.MESSAGE);

        this.message = null;
        this.byteString = bytes;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @Nullable
    public ByteString getBytes() {
        return byteString;
    }

    public boolean isEmpty(){
        return  byteString == null;
    }

    @Override
    public String toString() {
        return "SocketEventMessage{" + "Message='" + message + '\'' + ", bytes=" + byteString + '}';
    }
}
