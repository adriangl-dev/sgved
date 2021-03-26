package com.tfm.sgved.service;

import com.tfm.sgved.model.Participant;
import com.tfm.sgved.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {
    @Autowired
    ParticipantRepository participantRepository;

    public Participant findByDniAndSurvey(String dni, int id_survey){
        return participantRepository.findByDniAndSurvey(dni,id_survey);
    }
}
