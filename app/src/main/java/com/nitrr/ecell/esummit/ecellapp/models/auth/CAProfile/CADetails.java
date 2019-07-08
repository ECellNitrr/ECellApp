package com.nitrr.ecell.esummit.ecellapp.models.auth.CAProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CADetails implements Serializable {

    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("college")
    @Expose
    String college;

    public String getToken() {
        return token;
    }

    public String getCollege() {
        return college;
    }
}
