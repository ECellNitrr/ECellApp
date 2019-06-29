package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.SerializedName;

public class EventData {
    @SerializedName("id")
    private int pos;
    @SerializedName(/*"name"*/"completed")
    private String name;
//    @SerializedName("venue")
//    private String venue;
//    @SerializedName("date")
//    private String date;
//    @SerializedName("time")
//    private String time;
    @SerializedName(/*"details"*/"title")
    private String details;
    @SerializedName("icon")
    private String img;
//    @SerializedName("email")
//    private String email;
//    @SerializedName("flag")
//    private boolean flag;
    private Float alpha;

    public EventData() {
        //empty constructor
    }

    public EventData(int pos, String name, String venue, String date, String time, String details, String img, String email, boolean flag) {
        this.pos = pos;
        this.name = name;
//        this.venue = venue;
//        this.date = date;
//        this.time = time;
        this.details = details;
        this.img = img;
//        this.email = email;
//        this.flag = flag;
    }

    public int getPos() {
        return pos;
    }

    public String getName() {
        return name;
    }
//
//    public String getVenue() {
//        return venue;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public String getTime() {
//        return time;
//    }

    public String getDetails() {
        return details;
    }

    public String getImage() {
        return img;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public boolean isFlag() {
//        return flag;
//    }

    public Float getAlpha() {
        return alpha;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String img) {
        this.img = img;
    }

    public void setAlpha(Float alpha) {
        this.alpha = alpha;
    }
}