package com.nitrr.ecell.esummit.ecellapp.models.sponsors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SponsorsModel {


    @SerializedName("token")
    @Expose
    String apptoken ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnQiOiJhbmRyb2lkIiwib3JnYW5pemF0aW9uIjoiRUNlbGwifQ.H2aaDJuOxK44D2kwRCWwv9s5rzJGCNYKT3thtQqN-hQ";

    @SerializedName("message")
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
