package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppDetails {

    @SerializedName("MessageModel")
    @Expose
    String log;

    @SerializedName("URL")
    @Expose
    String link;

    @SerializedName("version")
    @Expose
    Integer version;


    public String getLog() {
        return log;
    }

    public String getLink() {
        return link;
    }

    public Integer getVersion() {
        return version;
    }

}
