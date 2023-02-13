package com.upgrad.ratingservice.controller;

import com.upgrad.ratingservice.model.DoctorRating;
import com.upgrad.ratingservice.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RatingController {

    private RatingService ratingService;
    private ModelMapper modelMapper;
    @Autowired
    public RatingController(RatingService ratingService, ModelMapper modelMapper){
        this.ratingService = ratingService;
        this.modelMapper=modelMapper;
    }

    //End point to rate a doctor by a user
    @PostMapping("/ratings")
    public ResponseEntity rateDoctor(@RequestBody DoctorRating doctorRating) throws IOException {
        DoctorRating rating = this.ratingService.rateDoctor(doctorRating);
            return new ResponseEntity(rating, HttpStatus.OK);
    }
}
