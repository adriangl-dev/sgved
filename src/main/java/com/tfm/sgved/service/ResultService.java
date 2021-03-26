package com.tfm.sgved.service;

import com.tfm.sgved.model.Result;
import com.tfm.sgved.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    ResultRepository resultRepository;

    public List<Result> findResultsBySurvey(int nsurvey) {
        return resultRepository.findBySurvey(nsurvey);
    }
}