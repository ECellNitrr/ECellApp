package com.nitrr.ecell.esummit.ecellapp.models.forgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhoneNumber {

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("contact")
    @Expose
    String number;

    public PhoneNumber(String email, String number) {
        this.email = email;
        this.number = number;
    }

}
