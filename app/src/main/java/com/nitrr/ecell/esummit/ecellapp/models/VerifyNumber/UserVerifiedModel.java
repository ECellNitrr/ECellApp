package com.nitrr.ecell.esummit.ecellapp.models.VerifyNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserVerifiedModel {

    @SerializedName("verified")
    @Expose
    Boolean verified;

    public Boolean getVerified() {
        return verified;
    }
}
