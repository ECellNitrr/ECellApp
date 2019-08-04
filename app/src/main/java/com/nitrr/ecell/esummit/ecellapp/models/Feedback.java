package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feedback {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("message")
    @Expose
    private String message;


}
