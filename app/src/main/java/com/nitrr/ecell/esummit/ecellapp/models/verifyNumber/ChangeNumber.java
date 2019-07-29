package com.nitrr.ecell.esummit.ecellapp.models.verifyNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeNumber {

    @SerializedName("contact")
    @Expose
    private
    String number;

    public ChangeNumber(String number) {
        this.number = number;
    }

}
