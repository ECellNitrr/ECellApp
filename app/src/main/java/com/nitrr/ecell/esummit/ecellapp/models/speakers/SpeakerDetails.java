package com.nitrr.ecell.esummit.ecellapp.models.speakers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpeakerDetails implements Serializable {

    public SpeakerDetails(String name, String image) {
        this.name = name;
        this.image = image;
    }

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("company")
    @Expose
    String company;

    @SerializedName("experience")
    @Expose
    int experience;

    @SerializedName("profile_pic")
    @Expose
    String image;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("contact")
    @Expose
    String contact;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("social_media")
    @Expose
    String socialMedia;

    @SerializedName("flag")
    @Expose
    boolean flag;

    @SerializedName("year")
    @Expose
    int year;

    public SpeakerDetails(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public boolean isFlag() {
        return flag;
    }
}
