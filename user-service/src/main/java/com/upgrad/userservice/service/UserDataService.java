package com.upgrad.userservice.service;

import com.upgrad.userservice.exception.RequestedResourceNotFoundException;
import com.upgrad.userservice.model.User;
import com.upgrad.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDataService {

    @Autowired
    UserRepository userRepository;
    //method to find user details by User ID
    public User findUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new RequestedResourceNotFoundException();
        }
    }
}
