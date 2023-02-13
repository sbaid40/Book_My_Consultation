package com.upgrad.paymentservice.controller;

import com.upgrad.paymentservice.dto.PaymentDetails;
import com.upgrad.paymentservice.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private PaymentService paymentService;
    private ModelMapper modelMapper;
    @Autowired
    public PaymentController(PaymentService paymentService, ModelMapper modelMapper){
        this.paymentService = paymentService;
        this.modelMapper=modelMapper;
    }

    //End point to accept payment for the appointment ID
    @PostMapping("/payments")
    public ResponseEntity receivePayment(@RequestParam String appointmentId){
        this.paymentService.receivePayment(appointmentId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
