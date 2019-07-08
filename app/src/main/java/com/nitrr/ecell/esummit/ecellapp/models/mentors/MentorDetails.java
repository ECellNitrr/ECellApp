package com.nitrr.ecell.esummit.ecellapp.models.mentors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MentorDetails implements Serializable {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("contact")
    @Expose
    String contact;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("detail")
    @Expose
    String detail;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("profile_pic")
    @Expose
    int profilePic;

    @SerializedName("year")
    @Expose
    int year;

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getDetail() {
        return detail;
    }

    public String getDescription() {
        return description;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public int getYear() {
        return year;
    }
}
