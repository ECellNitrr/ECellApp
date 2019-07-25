package com.nitrr.ecell.esummit.ecellapp.models.auth;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthResponse implements Serializable {

    @SerializedName("message")
    @Expose
    private
    String message;

    @SerializedName("token")
    @Expose
    private
    String token;

    @SerializedName("details")
    @Expose
    private
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

    @NonNull
    @Override
    public String toString() {
        return "AuthResponse{" +
                "Message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
