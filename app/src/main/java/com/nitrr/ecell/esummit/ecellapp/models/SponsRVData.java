package com.nitrr.ecell.esummit.ecellapp.models;


import com.google.gson.annotations.SerializedName;


public class SponsRVData{
    @SerializedName("title")
    String name;
    @SerializedName("id")
    String type;
    int res;
    @SerializedName("thumbnailUrl")
    String img;

    public SponsRVData(String name, String type, int res, String img) {
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
    public String getImg(){
        return img;
    }
}
