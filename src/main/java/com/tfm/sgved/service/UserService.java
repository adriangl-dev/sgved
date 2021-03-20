package com.tfm.sgved.service;

import com.tfm.sgved.model.User;
import com.tfm.sgved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
@Autowired
UserRepository userRepository;

//alta usuario
public void saveOrUpdate(User user)
{
    userRepository.save(user);
}

}
