package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppDetails {

    @SerializedName("message")
    @Expose
    private
    String log;

    @SerializedName("URL")
    @Expose
    private
    String link;

    @SerializedName("version")
    @Expose
    private
    Integer version;

    @SerializedName("i_date")
    @Expose
    private
    String sdate;

    @SerializedName("f_date")
    @Expose
    private
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

    public String getStartingDate() {
        return sdate;
    }

    public String getEndingDate() {
        return fdate;
    }
}
