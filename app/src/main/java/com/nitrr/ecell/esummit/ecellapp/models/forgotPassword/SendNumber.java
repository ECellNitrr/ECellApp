package com.nitrr.ecell.esummit.ecellapp.models.forgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendNumber {

    @SerializedName("id")
    @Expose
    String token;

    @SerializedName("mobile")
    @Expose
    String mobile;
}
