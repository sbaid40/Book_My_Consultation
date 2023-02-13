package com.upgrad.doctorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
public class DoctorRating {

    private String doctorId;
    private int rating;
}

