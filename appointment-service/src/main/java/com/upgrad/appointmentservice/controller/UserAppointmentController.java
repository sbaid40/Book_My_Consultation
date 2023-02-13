package com.upgrad.appointmentservice.controller;

import com.upgrad.appointmentservice.dto.AppointmentDetails;
import com.upgrad.appointmentservice.entity.Appointment;
import com.upgrad.appointmentservice.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAppointmentController {

    private AppointmentService appointmentService;
    private ModelMapper modelMapper;
    @Autowired
    public UserAppointmentController(AppointmentService appointmentService, ModelMapper modelMapper){
        this.appointmentService = appointmentService;
        this.modelMapper=modelMapper;
    }
    //End point to get appointment details of the User using user ID
    @GetMapping("/users/{userId}/appointments")
    public ResponseEntity getUserAppointmentDetails(@PathVariable("userId") String userId){
        List<Appointment> userAppointmentDetails = this.appointmentService.getUserAppointmentDetails(userId);
        return new ResponseEntity(userAppointmentDetails, HttpStatus.OK);
    }

    //End point to update the payment status of the appointment using appointment id
    @PostMapping("/payment/{appointmentId}")
    public ResponseEntity updatePaymentDetails(@PathVariable("appointmentId") String appintmentId){
        this.appointmentService.updatePaymentStatus(appintmentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
