package com.tfm.sgved.service;

import com.tfm.sgved.model.Result;
import com.tfm.sgved.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResultService {
    @Autowired
    ResultRepository resultRepository;

    public List<Result> findResultsBySurvey(int nsurvey) {
        return resultRepository.findBySurvey(nsurvey);
    }

    public void save(Result result){
        resultRepository.save(result);
    }

    public HashMap<String,String> stats(int nsurvey) {
        HashMap<String, String> map = new HashMap<>();
        List<Object[]> lista = resultRepository.stats(nsurvey);
        lista.stream().forEach(e -> map.put(e[0].toString(),e[1].toString()));

        return map;
    }
}