package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackModel {

    public FeedbackModel(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("message")
    @Expose
    private String message;
}
