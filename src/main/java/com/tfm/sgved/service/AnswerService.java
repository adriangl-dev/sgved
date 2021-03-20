package com.tfm.sgved.service;

import com.tfm.sgved.model.Answer;
import com.tfm.sgved.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    //alta y edicion respuesta
    public void saveOrUpdate(Answer answer)
    {
        answerRepository.save(answer);
    }
    //obtener pregunta
    public Answer getQuestionById(int id)
    {
        return answerRepository.findById(id).get();
    }

    //obtener todas las respuestas
    public List<Answer> getAllAnswers()
    {
        List<Answer> answers = new ArrayList<Answer>();
        answerRepository.findAll().forEach(answer -> answers.add(answer));
        return answers;
    }

    //borrar respuesta
    public void delete(int id)
    {
        answerRepository.deleteById(id);
    }

}