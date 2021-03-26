package com.tfm.sgved.repository;

import com.tfm.sgved.model.Participant;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Integer>{

    Participant findByDniAndNsurvey(String dni, int nsurvey);
}