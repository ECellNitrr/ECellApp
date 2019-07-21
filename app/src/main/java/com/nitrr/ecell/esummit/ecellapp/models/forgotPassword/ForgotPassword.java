package com.nitrr.ecell.esummit.ecellapp.models.forgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ForgotPassword implements Serializable {

    @SerializedName("email")
    @Expose
    private String email;

    public ForgotPassword(String email) {
        this.email = email;
    }
}
