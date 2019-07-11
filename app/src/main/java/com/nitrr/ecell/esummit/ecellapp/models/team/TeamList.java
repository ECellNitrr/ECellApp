package com.nitrr.ecell.esummit.ecellapp.models.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamList {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("url")
    @Expose
    String url;

    @SerializedName("member_type")
    @Expose
    String type;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }
}
