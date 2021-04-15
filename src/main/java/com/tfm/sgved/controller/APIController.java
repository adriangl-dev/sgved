package com.tfm.sgved.controller;

import com.tfm.sgved.model.Participant;
import com.tfm.sgved.model.Survey;
import com.tfm.sgved.service.ParticipantService;
import com.tfm.sgved.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {
    @Autowired
    SurveyService surveyService;
    @Autowired
    ParticipantService participantService;

    @GetMapping("/rest/survey/all")
    private List<Survey> getAllSurveys(){
        return surveyService.getAllSurveys();
    }

    @PostMapping("/rest/survey/add")
    private int saveSurvey(@RequestBody Survey survey){
        surveyService.saveOrUpdate(survey);
        return survey.getId();
    }

    @GetMapping("/rest/survey/{id}")
    private Survey getSurvey(@PathVariable("id") int id){
        return surveyService.getSurveyById(id);
    }

    @GetMapping("/rest/check_auth/{dni}/{key}/{survey}")
    private String checkAuth(@PathVariable("dni") String dni, @PathVariable("key") String key, @PathVariable("survey") int nsurvey){
        String res = "KO";
        Participant data = participantService.findByDniAndSurvey(dni,nsurvey);
        Survey survey = surveyService.getSurveyById(nsurvey);
        if(data != null && key.equals(data.getKey()) && !data.isFilled() && !survey.isEditable()) res = "OK";
        return res;
    }

    @GetMapping("rest/publish/{id}")
    public void publishSurvey(@PathVariable("id") int id){
        Survey survey = surveyService.getSurveyById(id);
        survey.setEditable(false);
        surveyService.saveOrUpdate(survey);
    }
}