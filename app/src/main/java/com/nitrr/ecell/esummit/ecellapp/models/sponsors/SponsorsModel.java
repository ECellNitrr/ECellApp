package com.nitrr.ecell.esummit.ecellapp.models.sponsors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SponsorsModel {

    @SerializedName("MessageModel")
    @Expose
    String mesage;
    @SerializedName("data")
    @Expose
    List<com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsRVData> list;

    public String getMesage() {
        return mesage;
    }

    public List<com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsRVData> getList() {
        return list;
    }
}
