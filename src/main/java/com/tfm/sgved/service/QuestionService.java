package com.tfm.sgved.service;

import com.tfm.sgved.model.Question;
import com.tfm.sgved.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    //alta y edicion pregunta
    public void saveOrUpdate(Question survey)
    {
        questionRepository.save(survey);
    }
    //obtener pregunta
    public Question getQuestionById(int id)
    {
        return questionRepository.findById(id).get();
    }

    //obtener todas las preguntas
    public List<Question> getAllQuestions()
    {
        List<Question> questions = new ArrayList<Question>();
        questionRepository.findAll().forEach(question -> questions.add(question));
        return questions;
    }

    //borrar pregunta
    public void delete(int id)
    {
        questionRepository.deleteById(id);
    }

}