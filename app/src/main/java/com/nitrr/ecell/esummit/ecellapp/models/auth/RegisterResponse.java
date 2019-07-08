package com.nitrr.ecell.esummit.ecellapp.models.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterResponse implements Serializable {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("token")
    @Expose
    String token;

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
