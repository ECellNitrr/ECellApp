package com.nitrr.ecell.esummit.ecellapp.models.startUps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StartUpDetails implements Serializable {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("year")
    @Expose
    int year;

    @SerializedName("url")
    @Expose
    String url;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("contact")
    @Expose
    String contact;

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("detail")
    @Expose
    String detail;

    @SerializedName("founder")
    @Expose
    String founder;

    @SerializedName("pic")
    @Expose
    int pic;

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getUrl() {
        return url;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String getDetail() {
        return detail;
    }

    public String getFounder() {
        return founder;
    }

    public int getPic() {
        return pic;
    }
}
