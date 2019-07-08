package com.nitrr.ecell.esummit.ecellapp.models.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginDetails implements Serializable {

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("password")
    @Expose
    String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
