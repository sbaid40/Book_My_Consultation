package com.upgrad.doctorservice.dao;

import com.upgrad.doctorservice.model.ApplicationUser;

public interface ApplicationUserDao {
    public ApplicationUser loadUserByUsername(String s);
}
