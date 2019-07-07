package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.SerializedName;

public class TeamRVdata {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("member_type")
    String post;
    @SerializedName("image")
    String img;

    public TeamRVdata(int id, String name, String post, String img) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public String getImg() {
        return img;
    }
}
