package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BquizLiveCheckResponse {

    @SerializedName("live")
    @Expose
    public boolean live;
}
