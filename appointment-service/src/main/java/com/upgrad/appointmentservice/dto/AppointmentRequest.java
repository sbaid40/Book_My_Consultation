package com.upgrad.appointmentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRequest {
    private String doctorId;
    private String userId;
    private String appointmentDate;
    private String timeSlot;
}
