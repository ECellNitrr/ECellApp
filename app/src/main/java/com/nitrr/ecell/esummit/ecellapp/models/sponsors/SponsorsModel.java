package com.nitrr.ecell.esummit.ecellapp.models.sponsors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SponsorsModel {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    List<SponsRVData> list;

    @SerializedName("spons_categories")
    @Expose
    String[] sponstype;
    public String getMessage() {
        return message;
    }

    public List<SponsRVData> getList() {
        return list;
    }
}
