package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionDetailsModel implements Serializable {
    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("key")
    @Expose
    private int key;

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
