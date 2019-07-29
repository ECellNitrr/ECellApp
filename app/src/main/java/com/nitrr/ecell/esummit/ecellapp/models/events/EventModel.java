package com.nitrr.ecell.esummit.ecellapp.models.events;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EventModel implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<EventData> list;

    public String getMessage() {
        return message;
    }

    public List<EventData> getList() {
        return list;
    }
}
