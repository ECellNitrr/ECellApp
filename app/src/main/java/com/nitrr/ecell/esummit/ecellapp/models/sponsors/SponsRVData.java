package com.nitrr.ecell.esummit.ecellapp.models.sponsors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SponsRVData{

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("spons_type")
    @Expose
    private String type;

    @SerializedName("importance")
    @Expose
    private int imp;

    @SerializedName("category_importance")
    @Expose
    private int ctgImp;

    @SerializedName("pic_url")
    @Expose
    private String img;

    @SerializedName("year")
    @Expose
    private int year;

    @SerializedName("website")
    @Expose
    private String website;

    public SponsRVData(String name, String id, String type, String img, int year, String website,int imp) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.img = img;
        this.year = year;
        this.website = website;
        this.imp = imp;
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

    public int getYear() {
        return year;
    }

    public String getWebsite() {
        return website;
    }

    public int getImp() {
        return imp;
    }

    public int getCtgImp() {
        return ctgImp;
    }
}
