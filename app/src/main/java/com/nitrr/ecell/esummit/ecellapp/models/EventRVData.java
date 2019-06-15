package com.nitrr.ecell.esummit.ecellapp.models;

import android.widget.ImageView;

import java.io.Serializable;

public class EventRVData implements Serializable {
    private String name;
    private int img;
    private Float alpha;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return img;
    }

    public void setImage(int image) {
        this.img = image;
    }

    public void setAlpha(Float alpha){
        this.alpha = alpha;
    }

    public Float getAlpha(){
        return alpha;
    }
}
