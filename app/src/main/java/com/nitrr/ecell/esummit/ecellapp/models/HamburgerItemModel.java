package com.nitrr.ecell.esummit.ecellapp.models;

import android.view.View;

import java.io.Serializable;

public class HamburgerItemModel implements Serializable {

    private String name;
    private int img;
    private View.OnClickListener listener;

    public HamburgerItemModel(String name, int img, View.OnClickListener listener) {
        this.name = name;
        this.img = img;
        this.listener = listener;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }

    public View.OnClickListener getListener() {
        return listener;
    }
}
