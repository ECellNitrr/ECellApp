package com.nitrr.ecell.esummit.ecellapp.models.events;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventData {
    @SerializedName("id")
    @Expose
    private int id;

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

    @SerializedName("icon_url")
    @Expose
    private String img;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("flag")
    @Expose
    private boolean flag;

    @SerializedName("registered")
    @Expose
    private boolean registered;

    @SerializedName("no_of_ppl_registered")
    @Expose
    private int noOfRegistrations;


    public int getId() {
        return id;
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

    public boolean isRegistered() {
        return registered;
    }

    public int getNoOfRegistrations() {
        return noOfRegistrations;
    }
}