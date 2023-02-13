package com.upgrad.doctorservice.controller.advice;

import com.upgrad.doctorservice.exception.RequestedResourceNotFoundException;
import com.upgrad.doctorservice.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {

    //Handler to handle all the resource not found exception
    @ExceptionHandler(RequestedResourceNotFoundException.class)
    ResponseEntity<ErrorModel> handleRequestedResourceNotFoundException() {
        return new ResponseEntity(ErrorModel
                .builder()
                .errorCode("ERR_RESOURCE_001")
                .errorMessage("Resource Not Found")
                .build(), HttpStatus.BAD_REQUEST);
    }

    //Handler to handle all the bad request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity(ErrorModel
                .builder()
                .errorCode("ERR_INVALID_PAYLOAD_001")
                .errorMessage("Invalid Payload in the request")
                .errorFieldsList(e.getBindingResult().getFieldErrors().stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList()))
                .build(), HttpStatus.BAD_REQUEST);
    }
}


