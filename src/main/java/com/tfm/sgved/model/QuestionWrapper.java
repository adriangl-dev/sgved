package com.tfm.sgved.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
public class QuestionWrapper extends Question {
    public ArrayList<Answer> answersTemp = new ArrayList<Answer>();

    public ArrayList<Answer> getAnswersTemp(){
        return this.answersTemp;
    }
    public void setAnswersTemp(ArrayList<Answer> answersTemp){
        this.answersTemp = answersTemp;
    }
}
