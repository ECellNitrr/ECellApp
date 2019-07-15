package com.nitrr.ecell.esummit.ecellapp.models.sponsors;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SponsRVData{
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("spons_type")
    @Expose
    String type;
    @SerializedName("pic")
    @Expose
    String img;

    public String getName(){
        return name;
    }
    public  String getType(){
        return type;
    }
    public String getId(){
        return id;
    }
    public String getImg(){
        return img;
    }
}
