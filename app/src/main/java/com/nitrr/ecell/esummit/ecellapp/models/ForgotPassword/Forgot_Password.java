package com.nitrr.ecell.esummit.ecellapp.models.ForgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Forgot_Password implements Serializable {

    @SerializedName("email")
    @Expose
    private String email;

    public Forgot_Password(String email) {
        this.email = email;
    }
}
