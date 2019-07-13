package com.nitrr.ecell.esummit.ecellapp.models;

import android.view.View;

import java.io.Serializable;

public class HomeRVData implements Serializable {

    private String name;
    private String color;
    private int image;
    private View.OnClickListener listener;

    public HomeRVData(String name, String color, int image, View.OnClickListener listener) {
        this.name = name;
        this.color = color;
        this.image = image;
        this.listener = listener;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getImage() {
        return image;
    }

    public View.OnClickListener getListener() {
        return listener;
    }
}