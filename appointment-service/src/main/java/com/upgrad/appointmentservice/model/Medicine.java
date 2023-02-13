package com.upgrad.appointmentservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medicine {
    private String name;
    private String frequency;
    private String dosage;
    private String remarks;
}
