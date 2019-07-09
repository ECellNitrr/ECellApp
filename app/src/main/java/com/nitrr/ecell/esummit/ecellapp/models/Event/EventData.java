package com.nitrr.ecell.esummit.ecellapp.models.Event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventData {
    @SerializedName("id")
    @Expose
    private int pos;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("icon")
    @Expose
    private String img;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("flag")
    @Expose
    private boolean flag;

    public int getPos() {
        return pos;
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDetails() {
        return details;
    }

    public String getImage() {
        return img;
    }

    public String getEmail() {
        return email;
    }

    public boolean isFlag() {
        return flag;
    }

}