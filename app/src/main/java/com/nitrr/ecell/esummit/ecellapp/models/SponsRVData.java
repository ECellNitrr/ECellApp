package com.nitrr.ecell.esummit.ecellapp.models;

import android.net.Uri;

public class SponsRVData {
    String name;
    String type;
    int res;
    Uri img;

    public SponsRVData(String name, String type, int res, Uri img) {
        this.name = name;
        this.type = type;
        this.res = res;
        this.img = img;
    }

    public String getName(){
        return name;
    }
    public  String getType(){
        return type;
    }
    public int getRes(){
        return res;
    }
    public Uri getImg(){
        return img;
    }
}
