package com.nitrr.ecell.esummit.ecellapp.models.speakers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpeakerResponseList implements Serializable {
    @SerializedName("index")
    @Expose
    SpeakerResponseData data;
}
