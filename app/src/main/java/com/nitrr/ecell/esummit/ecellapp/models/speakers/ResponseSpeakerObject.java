package com.nitrr.ecell.esummit.ecellapp.models.speakers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseSpeakerObject implements Serializable {

    public ResponseSpeakerObject(ResponseSpeakerData data) {
        this.data = data;
    }

    @SerializedName("index")
    @Expose
    ResponseSpeakerData data;

    public ResponseSpeakerData getData() {
        return data;
    }
}
