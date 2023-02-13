package com.upgrad.ratingservice.dao;

import com.upgrad.ratingservice.model.ApplicationUser;

public interface ApplicationUserDao {
    public ApplicationUser loadUserByUsername(String s);
}
