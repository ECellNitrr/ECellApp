package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resendotp {

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("otp")
    @Expose
    String otp;

}
