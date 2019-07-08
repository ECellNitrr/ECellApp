package com.nitrr.ecell.esummit.ecellapp.models.speakers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpeakerDetails implements Serializable {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("company")
    @Expose
    String company;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("year")
    @Expose
    int year;

    @SerializedName("social_media")
    @Expose
    String socialMedia;

    @SerializedName("experience")
    @Expose
    int experience;

    @SerializedName("contact")
    @Expose
    String contact;

    @SerializedName("description")
    @Expose
    String description;


    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public int getYear() {
        return year;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public int getExperience() {
        return experience;
    }

    public String getContact() {
        return contact;
    }

    public String getDescription() {
        return description;
    }
}
