package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BquizLeaderBoardResponse implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<LeaderBoardUserDetail> leaderboard;

    public BquizLeaderBoardResponse(String message, List<LeaderBoardUserDetail> leaderboard) {
        this.message = message;
        this.leaderboard = leaderboard;
    }

    public String getMessage() {
        return message;
    }

    public List<LeaderBoardUserDetail> getLeaderboard() {
        return leaderboard;
    }
}
