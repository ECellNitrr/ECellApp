package com.nitrr.ecell.esummit.ecellapp.models.mentors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MentorResponse implements Serializable {
    @SerializedName("MessageModel")
    @Expose
    String message;

    @SerializedName("list")
    @Expose
    MentorResponseList list;

    public String getMessage() {
        return message;
    }

    public MentorResponseList getList() {
        return list;
    }
}
