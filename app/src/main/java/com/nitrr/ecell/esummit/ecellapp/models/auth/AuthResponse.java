package com.nitrr.ecell.esummit.ecellapp.models.auth;

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

    @SerializedName("detail")
    @Expose
    private
    String details;

    @SerializedName("first_name")
    @Expose
    private
    String firstName;

    @SerializedName("last_name")
    @Expose
    private
    String lastName;

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getDetails() {
        return details;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
