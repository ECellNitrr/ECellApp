package com.nitrr.ecell.esummit.ecellapp.models.verifyNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserVerifiedModel {

    @SerializedName("verified")
    @Expose
    private
    Boolean verified;

    public Boolean getUserIsVerified() {
        return verified;
    }
}
