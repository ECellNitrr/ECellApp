package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LeaderBoardUserDetail implements Serializable {

    @SerializedName("score")
    @Expose
    private int score;

    @SerializedName("questionset")
    @Expose
    private int questionset;

    @SerializedName("user")
    @Expose
    private int user;


    @SerializedName("username")
    @Expose
    private String name;

    public LeaderBoardUserDetail(int score, int questionset, int user, String name) {
        this.score = score;
        this.questionset = questionset;
        this.user = user;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

}