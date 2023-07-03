package com.example.augmentedexercise.model;

public class FaqsModel {
    private String question, answer;
    private boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public FaqsModel(String question, String answer, boolean expandable) {
        this.question = question;
        this.answer = answer;
        this.expandable = expandable;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "FaqsModel{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", expandable=" + expandable +
                '}';
    }
}
