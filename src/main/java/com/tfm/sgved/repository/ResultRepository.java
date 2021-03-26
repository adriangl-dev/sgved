package com.tfm.sgved.repository;

import com.tfm.sgved.model.Result;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResultRepository extends CrudRepository<Result, Integer>{
    List<Result> findBySurvey(int nsurvey);
}