package com.nitrr.ecell.esummit.ecellapp.models.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamList {

    public String getImage() {
        return image;
    }

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("domain")
    @Expose
    String domain;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("profile_url")
    @Expose
    String url;

    @SerializedName("member_type")
    @Expose
    String type;

    public String getName() {
        return name;
    }

    public String getImg() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getDomain() {
        return domain;
    }

    //Executives and Managers ka image and profile_url nai dikhana
}
