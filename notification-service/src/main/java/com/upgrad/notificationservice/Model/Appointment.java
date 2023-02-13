package com.upgrad.notificationservice.Model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
public class Appointment {

    private String appointmentId;
    private LocalDate appointmentDate;
    private LocalDateTime createdDate;
    private String doctorId;
    private String priorMedicalHistory;
    private String status;
    private String symptoms;
    private String timeSlot;
    private String userId;
    private String userEmailId;
    private String userName;
    private String doctorName;

}
