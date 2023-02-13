package com.upgrad.paymentservice.dao;

import com.upgrad.paymentservice.model.ApplicationUser;

public interface ApplicationUserDao {
    public ApplicationUser loadUserByUsername(String s);
}
