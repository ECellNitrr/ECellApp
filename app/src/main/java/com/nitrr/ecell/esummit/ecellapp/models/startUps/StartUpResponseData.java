package com.nitrr.ecell.esummit.ecellapp.models.startUps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StartUpResponseData implements Serializable {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("contact")
    @Expose
    String contact;

    @SerializedName("pic")
    @Expose
    int pic;

    @SerializedName("url")
    @Expose
    String url;

    @SerializedName("founder")
    @Expose
    String founder;

    @SerializedName("year")
    @Expose
    int year;

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("flag")
    @Expose
    Boolean flag;

    @SerializedName("detail")
    @Expose
    String detail;

    @SerializedName("created_at")
    @Expose
    String createdAt;

    @SerializedName("modified_at")
    @Expose
    String modifiedAt;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public int getPic() {
        return pic;
    }

    public String getUrl() {
        return url;
    }

    public String getFounder() {
        return founder;
    }

    public int getYear() {
        return year;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getFlag() {
        return flag;
    }

    public String getDetail() {
        return detail;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }
}
