package com.nitrr.ecell.esummit.ecellapp.models.forgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ForgotVerifyOTP implements Serializable {

    public ForgotVerifyOTP(String otp, String email) {
        this.otp = otp;
        this.email = email;
    }

    @SerializedName("token")
    @Expose
    String appToken ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnQiOiJhbmRyb2lkIiwib3JnYW5pemF0aW9uIjoiRUNlbGwifQ.H2aaDJuOxK44D2kwRCWwv9s5rzJGCNYKT3thtQqN-hQ";

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("otp")
    @Expose
    private String otp;
}
