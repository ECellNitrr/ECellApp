package com.nitrr.ecell.esummit.ecellapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HomeRVData implements Serializable {
    private String name;
    private int image;

    public HomeRVData(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
