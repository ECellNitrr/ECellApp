package com.nitrr.ecell.esummit.ecellapp.models.mentors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MentorResponseData implements Serializable {

    @SerializedName("id")
    @Expose
    int id;

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

    @SerializedName("flag")
    @Expose
    Boolean flag;

    @SerializedName("created_at")
    @Expose
    String createdAt;

    @SerializedName("modified_at")
    @Expose
    String modifiedAt;

    @SerializedName("year")
    @Expose
    int year;

    public int getId() {
        return id;
    }

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

    public Boolean getFlag() {
        return flag;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public int getYear() {
        return year;
    }
}
