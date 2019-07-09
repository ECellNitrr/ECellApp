package com.nitrr.ecell.esummit.ecellapp.models.Sponsors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SponsorsModel {


    @SerializedName("message")
    @Expose
    String mesage;
    @SerializedName("data")
    @Expose
    List<SponsRVData> list;

    public String getMesage() {
        return mesage;
    }

    public List<SponsRVData> getList() {
        return list;
    }
}
