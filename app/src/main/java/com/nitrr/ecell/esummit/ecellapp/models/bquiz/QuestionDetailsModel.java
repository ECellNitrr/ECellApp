package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionDetailsModel {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("end")
    @Expose
    public boolean end;

    @SerializedName("show")
    @Expose
    public boolean show;

    @SerializedName("question")
    @Expose
    public String question;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("meta")
    @Expose
    public String meta;

    @SerializedName("time_limit")
    @Expose
    public int timeLimit;

    @SerializedName("score")
    @Expose
    public int score;

    @SerializedName("options")
    @Expose
    public List<String> options;

    @SerializedName("option_id")
    @Expose
    public List<Integer> optionId;
}
