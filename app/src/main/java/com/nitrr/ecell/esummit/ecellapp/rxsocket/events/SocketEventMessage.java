package com.nitrr.ecell.esummit.ecellapp.rxsocket.events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nitrr.ecell.esummit.ecellapp.models.bquiz.BquizAnswerModel;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.SocketEventType;

import okio.ByteString;

public class SocketEventMessage extends SocketEvent {

    private final String message;
    private final ByteString byteString;
    private final BquizAnswerModel model;

    public SocketEventMessage(@NonNull String message) {
        super(SocketEventType.MESSAGE);

        this.message = message;
        this.byteString = null;
        this.model = null;
    }

    public SocketEventMessage(@NonNull BquizAnswerModel model){
        super(SocketEventType.MESSAGE);

        this.model = model;
        this.message = null;
        this.byteString = null;
    }

    public SocketEventMessage(@NonNull ByteString bytes){
        super(SocketEventType.MESSAGE);

        this.message = null;
        this.byteString = bytes;
        this.model = null;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @Nullable
    public BquizAnswerModel getModel() {
        return model;
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
