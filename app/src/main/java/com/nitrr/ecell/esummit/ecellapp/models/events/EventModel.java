package com.nitrr.ecell.esummit.ecellapp.models.events;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EventModel implements Serializable {

    @SerializedName("token")
    @Expose
    String apptoken ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnQiOiJhbmRyb2lkIiwib3JnYW5pemF0aW9uIjoiRUNlbGwifQ.H2aaDJuOxK44D2kwRCWwv9s5rzJGCNYKT3thtQqN-hQ";


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
