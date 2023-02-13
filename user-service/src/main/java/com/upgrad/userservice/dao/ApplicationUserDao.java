package com.upgrad.userservice.dao;

import com.upgrad.userservice.model.ApplicationUser;

public interface ApplicationUserDao {
    public ApplicationUser loadUserByUsername(String s);
}
