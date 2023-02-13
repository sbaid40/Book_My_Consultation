package com.upgrad.ratingservice.controller.advice;

import com.upgrad.ratingservice.exception.RatingValueInvalidException;
import com.upgrad.ratingservice.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(RatingValueInvalidException.class)
    ResponseEntity<ErrorModel> handleRequestedResourceNotFoundException() {
        return new ResponseEntity(ErrorModel
                .builder()
                .errorCode("ERR_RATING_001")
                .errorMessage("Rating value should be between 1 and 5")
                .build(), HttpStatus.BAD_REQUEST);
    }
}


