package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeNumber {
    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("number")
    @Expose
    String number;

    public ChangeNumber(String token, String number) {
        this.token = token;
        this.number = number;
    }
}
