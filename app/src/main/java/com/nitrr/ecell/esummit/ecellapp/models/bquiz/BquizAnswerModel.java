package com.nitrr.ecell.esummit.ecellapp.models.bquiz;

public class BquizAnswerModel {

    private String text;

    public BquizAnswerModel(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
