package com.upgrad.notificationservice.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User {
    private String id ;
    private String firstName;
    private String lastName;
    private String dob;
    private String mobile;
    private String emailId;
    private LocalDate createdDate;
}
