package com.upgrad.ratingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@Document(collection="Rating")
public class DoctorRating {

    @Id
    private String doctorId;
    private int rating;
    @JsonIgnore
    private int numberOfPeopleRated;
    @JsonIgnore
    private int totalRating;
}

