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
    @SerializedName("pic_url")
    @Expose
    String img;

    public SponsRVData(String name, String id, String type, String img) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.img = img;
    }

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
