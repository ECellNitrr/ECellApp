package com.nitrr.ecell.esummit.ecellapp.models.speakers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpeakerResponse implements Serializable {
    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    SpeakerResponseList list;

    public String getMessage() {
        return message;
    }

    public SpeakerResponseList getList() {
        return list;
    }
}
