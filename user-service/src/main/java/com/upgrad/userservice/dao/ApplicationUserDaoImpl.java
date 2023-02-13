package com.upgrad.userservice.dao;

import com.upgrad.userservice.model.ApplicationRole;
import com.upgrad.userservice.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    @Autowired
    PasswordEncoder encoder;

    @Override
    public ApplicationUser loadUserByUsername(String username) {
        return loadAllUsers()
                .stream()
                .filter(u->u.getUsername().equalsIgnoreCase(username))
                .findFirst().orElseThrow(()-> new UsernameNotFoundException(username));
    }
    private List<ApplicationUser> loadAllUsers(){
        return Arrays.asList(
                ApplicationUser
                        .builder()
                        .username("user")
                        .password(encoder.encode("password"))
                        .authorities(ApplicationRole.USER.getAuthorities())
                        .build(),
                ApplicationUser
                        .builder()
                        .username("doctor")
                        .password(encoder.encode("password"))
                        .authorities(ApplicationRole.DOCTOR.getAuthorities())
                        .build(),
                ApplicationUser
                        .builder()
                        .username("admin")
                        .password(encoder.encode("password"))
                        .authorities(ApplicationRole.ADMIN.getAuthorities())
                        .build()
        );
    }
}
