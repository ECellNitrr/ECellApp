package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppDetails {

    @SerializedName("message")
    @Expose
    String log;

    @SerializedName("URL")
    @Expose
    String link;

    @SerializedName("version")
    @Expose
    Integer version;

    @SerializedName("idate")
    @Expose
    String sdate;

    @SerializedName("fdate")
    @Expose
    String fdate;

    public String getLog() {
        return log;
    }

    public String getLink() {
        return link;
    }

    public Integer getVersion() {
        return version;
    }

    public String getStartingdate() {
        return sdate;
    }

    public String getEndingdate() {
        return fdate;
    }
}
