package com.nitrr.ecell.esummit.ecellapp.models.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthResponse implements Serializable {

    @SerializedName("MessageModel")
    @Expose
    String message;

    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("details")
    @Expose
    String details;

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "MessageModel='" + message + '\'' +
                ", token='" + token + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
