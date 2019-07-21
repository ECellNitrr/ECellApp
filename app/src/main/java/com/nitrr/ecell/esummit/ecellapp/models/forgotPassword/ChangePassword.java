package com.nitrr.ecell.esummit.ecellapp.models.forgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePassword {

    @SerializedName("email")
    @Expose
    private
    String email;

    @SerializedName("token")
    @Expose
    private
    String token;

    @SerializedName("first_name")
    @Expose
    private
    String firstName;

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getFirstName() {
        return firstName;
    }
}
