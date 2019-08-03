package com.nitrr.ecell.esummit.ecellapp.models.forgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ForgotPassword implements Serializable {

    public ForgotPassword(String email) {
        this.email = email;
    }

    @SerializedName("email")
    @Expose
    private String email;
}
