package com.upgrad.paymentservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDetails {
    private String id;
    private String appointmentId;
    private String createdDate = LocalDateTime.now().toString();
}
