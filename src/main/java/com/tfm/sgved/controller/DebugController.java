package com.tfm.sgved.controller;

import com.tfm.sgved.model.Survey;
import com.tfm.sgved.service.AnswerService;
import com.tfm.sgved.service.QuestionService;
import com.tfm.sgved.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DebugController {
    @Autowired
    SurveyService surveyService;
    QuestionService questionService;
    AnswerService answerService;
    //ParticipantService participantService;

    @GetMapping("/survey/all")
    private List<Survey> getAllSurveys(){
        return surveyService.getAllSurveys();
    }
    @PostMapping("/survey/add")
    private int saveSurvey(@RequestBody Survey survey){
        surveyService.saveOrUpdate(survey);
        return survey.getId();
    }
    @GetMapping("/survey/{id}")
    private Survey getSurvey(@PathVariable("id") int id){
        return surveyService.getSurveyById(id);
    }
}
