package com.nitrr.ecell.esummit.ecellapp.models.startUps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StartUpResponse implements Serializable {
    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    StartUpResponseList list;

    public String getMessage() {
        return message;
    }

    public StartUpResponseList getList() {
        return list;
    }
}
