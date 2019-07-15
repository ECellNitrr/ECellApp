package com.nitrr.ecell.esummit.ecellapp.models.speakers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseSpeakerData implements Serializable {

    public ResponseSpeakerData(String name, String image) {
        this.name = name;
        this.image = image;
    }

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("profile_pic")
    @Expose
    String image;

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

    @SerializedName("flag")
    @Expose
    Boolean flag;

    @SerializedName("created_at")
    @Expose
    String createdAt;

    @SerializedName("modified_at")
    @Expose
    String modifiedAt;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
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

    public Boolean getFlag() {
        return flag;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }
}
