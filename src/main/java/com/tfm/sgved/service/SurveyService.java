package com.tfm.sgved.service;

import com.tfm.sgved.model.Survey;
import com.tfm.sgved.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyService {
    @Autowired
    SurveyRepository surveyRepository;

    //alta y edicion encuesta
    public void saveOrUpdate(Survey survey)
    {
        surveyRepository.save(survey);
    }
    //obtener encuesta
    public Survey getSurveyById(int id)
    {
        return surveyRepository.findById(id).get();
    }
    //obtener todas las encuestas
    public List<Survey> getAllSurveys() {
        List<Survey> surveys = new ArrayList<Survey>();
        surveyRepository.findAll().forEach(survey -> surveys.add(survey));
        return surveys;
    }

}