package com.upgrad.notificationservice.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class Doctor {

    private String id;
    private String firstName;
    private String lastName;
    private String speciality;
    private String dob;
    private String mobile;
    private String emailId;
    private String pan;
    private Status status;
    private String approvedBy;
    private String approverComments;
    private LocalDate registrationDate;
    private LocalDate verificationDate;
    private String rating;
}
