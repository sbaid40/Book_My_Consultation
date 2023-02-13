package com.upgrad.userservice.service;

import com.upgrad.userservice.model.User;
import com.upgrad.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOnboardingService {
    @Autowired
    UserRepository userRepository;
    //method to save user registration details
    public User saveUserDetails(User user) {
        User savedUser = this.userRepository.save(user);
        return savedUser;
    }
}
