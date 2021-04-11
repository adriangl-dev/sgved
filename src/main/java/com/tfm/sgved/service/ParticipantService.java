package com.tfm.sgved.service;

import com.tfm.sgved.model.Participant;
import com.tfm.sgved.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {
    @Autowired
    ParticipantRepository participantRepository;

    public Participant findByDniAndSurvey(String dni, int nsurvey){
        return participantRepository.findByDniAndNsurvey(dni,nsurvey);
    }
    public void saveParticipant(Participant participant){
        participantRepository.save(participant);
    }
    public List<Participant> findBySurvey(int nsurvey){
        return participantRepository.findByNsurvey(nsurvey);
    }
}
