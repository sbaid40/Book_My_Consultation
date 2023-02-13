package com.upgrad.appointmentservice.dao;

import com.upgrad.appointmentservice.model.ApplicationUser;

public interface ApplicationUserDao {
    public ApplicationUser loadUserByUsername(String s);
}
