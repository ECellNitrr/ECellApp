package com.nitrr.ecell.esummit.ecellapp.models.events;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventRegistrationModel {

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("eventName")
    @Expose
    String eventName;

    public EventRegistrationModel(String email, String eventName) {
        this.email = email;
        this.eventName = eventName;
    }
}
