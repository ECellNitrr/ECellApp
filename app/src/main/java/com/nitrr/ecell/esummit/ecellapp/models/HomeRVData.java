package com.nitrr.ecell.esummit.ecellapp.models;

import java.io.Serializable;

public class HomeRVData implements Serializable {

    private String name;
    private String color;
    private int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
