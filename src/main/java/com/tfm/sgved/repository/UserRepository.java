package com.tfm.sgved.repository;

import org.springframework.data.repository.CrudRepository;
import com.tfm.sgved.model.User;
public interface UserRepository extends CrudRepository<User, String>{
    public User findByEmail(String email);
}
