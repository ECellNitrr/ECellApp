package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BquizResponseModel {

    @SerializedName("score")
    @Expose
    public float score;

    @SerializedName("bonus")
    @Expose
    public float bonus;

    @SerializedName("total_score")
    @Expose
    public float totalScore;

    @SerializedName("message")
    @Expose
    public String message;

    @Override
    public String toString() {
        return "BquizResponseModel{" +
                "score=" + score +
                ", bonus=" + bonus +
                ", totalScore=" + totalScore +
                ", message='" + message + '\'' +
                '}';
    }
}
