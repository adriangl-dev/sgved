package com.tfm.sgved.repository;

import com.tfm.sgved.model.Participant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParticipantRepository extends CrudRepository<Participant, Integer>{
    Participant findByDniAndNsurvey(String dni, int nsurvey);
    List<Participant> findByNsurvey(int nsurvey);
}