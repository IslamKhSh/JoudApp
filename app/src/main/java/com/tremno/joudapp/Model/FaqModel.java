package com.tremno.joudapp.Model;

import java.io.Serializable;

public class FaqModel implements Serializable  {
    private String id = "";
    private String question = "";
    private String answer = "";

    public FaqModel() {
    }

    public FaqModel(String question,String answer) {
        this.setQuestion(question);
        this.setAnswer(answer);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
