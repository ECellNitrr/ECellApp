package com.nitrr.ecell.esummit.ecellapp.models;

import java.io.Serializable;

public class HomeRVData implements Serializable {

    private String name;
    private String color;
    private int image;

    public HomeRVData(String name, int image, String color) {
        this.name = name;
        this.image = image;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }

}
