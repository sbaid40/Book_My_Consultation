package com.upgrad.auth.dao;

import com.upgrad.auth.model.ApplicationUser;

public interface ApplicationUserDao {
    public ApplicationUser loadUserByUsername(String s);
}
