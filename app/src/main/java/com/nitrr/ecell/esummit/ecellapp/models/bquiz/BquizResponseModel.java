package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BquizResponseModel {

    @SerializedName("score")
    @Expose
    public int score;

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
                ", totalScore=" + totalScore +
                ", message='" + message + '\'' +
                '}';
    }
}
