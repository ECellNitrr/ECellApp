package com.nitrr.ecell.esummit.ecellapp.models.ForgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Change_Password {

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("frist_name")
    @Expose
    String fristName;

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getFristName() {
        return fristName;
    }
}
