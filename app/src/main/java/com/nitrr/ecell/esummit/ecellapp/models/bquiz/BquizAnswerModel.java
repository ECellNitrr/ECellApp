package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BquizAnswerModel {

    @SerializedName("id")
    public int id;

    @SerializedName("show")
    public boolean show;

    @SerializedName("question")
    public String question;

    @SerializedName("description")
    public String description;

    @SerializedName("meta")
    public String meta;

    @SerializedName("time_limit")
    public int time_limit;

    @SerializedName("score")
    public float score;

    @SerializedName("options")
    List<String> options;


    @SerializedName("option_id")
    List<Integer> optionId;

    public BquizAnswerModel(int id, boolean show, String question, String description, String meta, int time_limit, float score, List<String> options, List<Integer> optionId) {
        this.id = id;
        this.show = show;
        this.question = question;
        this.description = description;
        this.meta = meta;
        this.time_limit = time_limit;
        this.score = score;
        this.options = options;
        this.optionId = optionId;
    }

    @Override
    public String toString() {
        return "BquizAnswerModel{" +
                "id=" + id +
                ", show=" + show +
                ", question='" + question + '\'' +
                ", description='" + description + '\'' +
                ", meta='" + meta + '\'' +
                ", time_limit=" + time_limit +
                ", score=" + score +
                ", options=" + options +
                ", optionId=" + optionId +
                '}';
    }
}
