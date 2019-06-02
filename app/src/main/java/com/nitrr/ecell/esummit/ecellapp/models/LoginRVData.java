package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginRVData implements Serializable {
    private String name;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
