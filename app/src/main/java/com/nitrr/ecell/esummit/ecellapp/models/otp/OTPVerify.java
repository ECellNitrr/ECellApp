package com.nitrr.ecell.esummit.ecellapp.models.otp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OTPVerify implements Serializable {

    @SerializedName("otp")
    @Expose
    String otp;

    public String getOtp() {
        return otp;
    }
}
