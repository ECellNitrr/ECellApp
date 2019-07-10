package com.nitrr.ecell.esummit.ecellapp.models.Team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamData {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    List<TeamList> list;

    public String getMessage() {
        return message;
    }

    public List<TeamList> getList() {
        return list;
    }

}
