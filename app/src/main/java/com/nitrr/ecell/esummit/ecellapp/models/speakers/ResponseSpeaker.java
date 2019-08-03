package com.nitrr.ecell.esummit.ecellapp.models.speakers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseSpeaker implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private
    List<ResponseSpeakerData> list;

    public String getMessage() {
        return message;
    }

    public List<ResponseSpeakerData> getList() {
        return list;
    }
}
