package com.tfm.sgved.repository;

import com.tfm.sgved.model.Result;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ResultRepository extends CrudRepository<Result, Integer>{
    List<Result> findBySurvey(int nsurvey);

    @Query(value = "select a.id,count(*) from QUESTIONS q inner join ANSWERS a on q.id = a.nquestion inner join RESULTS r on r.answer = a.id where q.nsurvey = ?1 group by a.id order by a.id asc", nativeQuery = true)
    List<Object[]> stats(int nsurvey);
}