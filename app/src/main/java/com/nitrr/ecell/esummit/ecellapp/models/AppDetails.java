package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppDetails {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("log")
    @Expose
    String log;

    @SerializedName("link")
    @Expose
    String link;

    @SerializedName("version")
    @Expose
    Number version;

    @SerializedName("flag")
    @Expose
    boolean flag;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLog() {
        return log;
    }

    public String getLink() {
        return link;
    }

    public Number getVersion() {
        return version;
    }

    public boolean isFlag() {
        return flag;
    }
}
