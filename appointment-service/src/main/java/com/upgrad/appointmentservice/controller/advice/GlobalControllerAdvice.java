package com.upgrad.appointmentservice.controller.advice;

import com.upgrad.appointmentservice.exception.PaymentIncompleteException;
import com.upgrad.appointmentservice.exception.RequestedResourceNotFoundException;
import com.upgrad.appointmentservice.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(RequestedResourceNotFoundException.class)
    ResponseEntity<ErrorModel> handleRequestedResourceNotFoundException() {
        return new ResponseEntity(ErrorModel
                .builder()
                .errorCode("ERR_RESOURCE_001")
                .errorMessage("Resource Not Found")
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity(ErrorModel
                .builder()
                .errorCode("ERR_INVALID_PAYLOAD_001")
                .errorMessage("Invalid Payload in the request")
                .errorFieldsList(e.getBindingResult().getFieldErrors().stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList()))
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentIncompleteException.class)
    ResponseEntity<ErrorModel> handlePaymentIncompleteException() {
        return new ResponseEntity(ErrorModel
                .builder()
                .errorCode("ERR_PAYMENT_PENDING")
                .errorMessage("Prescription cannot be issued since payment status is pending")
                .build(), HttpStatus.BAD_REQUEST);
    }
}


