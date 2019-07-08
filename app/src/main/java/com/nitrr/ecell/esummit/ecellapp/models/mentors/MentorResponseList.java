package com.nitrr.ecell.esummit.ecellapp.models.mentors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MentorResponseList implements Serializable {
    @SerializedName("list")
    @Expose
    MentorResponseData data;
}
