package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BquizAnswerModel {

    @SerializedName("question_id")
    @Expose
    public int questionID;

    @SerializedName("answer_id")
    @Expose
    public int answerID;

    @SerializedName("time")
    public int time;
}
