package com.tfm.sgved.repository;

import com.tfm.sgved.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer>
{
}

