package com.tfm.sgved.service;

import com.tfm.sgved.model.User;
import com.tfm.sgved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //alta usuario
    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findById(email).orElse(null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }
}


