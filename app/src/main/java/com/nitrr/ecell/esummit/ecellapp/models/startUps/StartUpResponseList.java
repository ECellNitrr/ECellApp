package com.nitrr.ecell.esummit.ecellapp.models.startUps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StartUpResponseList implements Serializable {
    @SerializedName("index")
    @Expose
    StartUpResponseData data;
}
