package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionDetailsModel {

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
    public int timeLimit;

    @SerializedName("right_answer")
    @Expose
    public int rightAnswer;

    public int getRightAnswer() {
        return rightAnswer;
    }

    public int getId() {
        return id;
    }

    public boolean isShow() {
        return show;
    }

    public String getQuestion() {
        return question;
    }

    public String getDescription() {
        return description;
    }

    public String getMeta() {
        return meta;
    }

    public int getTime_limit() {
        return timeLimit;
    }

    public float getScore() {
        return score;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<Integer> getOptionId() {
        return optionId;
    }

    @SerializedName("score")
    public int score;

    @SerializedName("options")
    List<String> options;


    @SerializedName("option_id")
    List<Integer> optionId;

    public QuestionDetailsModel(int id, boolean show, String question, String description, String meta, int timeLimit, int rightAnswer, int score, List<String> options, List<Integer> optionId) {
        this.id = id;
        this.show = show;
        this.question = question;
        this.description = description;
        this.meta = meta;
        this.timeLimit = timeLimit;
        this.rightAnswer = rightAnswer;
        this.score = score;
        this.options = options;
        this.optionId = optionId;
    }

    @Override
    public String toString() {
        return "QuestionDetailsModel{" +
                "id=" + id +
                ", show=" + show +
                ", question='" + question + '\'' +
                ", description='" + description + '\'' +
                ", meta='" + meta + '\'' +
                ", timeLimit=" + timeLimit +
                ", rightAnswer=" + rightAnswer +
                ", score=" + score +
                ", options=" + options +
                ", optionId=" + optionId +
                '}';
    }
}
