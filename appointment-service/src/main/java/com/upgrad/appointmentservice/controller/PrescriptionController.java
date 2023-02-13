package com.upgrad.appointmentservice.controller;

import com.upgrad.appointmentservice.entity.Appointment;
import com.upgrad.appointmentservice.model.Prescription;
import com.upgrad.appointmentservice.service.AppointmentService;
import com.upgrad.appointmentservice.service.PrescriptionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private PrescriptionService prescriptionService;
    private ModelMapper modelMapper;

    //End point to send the prescription to User if payment is completed else give error
    @PostMapping("")
    public ResponseEntity sendPrescriptionToPatients(@RequestBody Prescription prescriptionDetails) throws IOException {
            this.prescriptionService.savePrescription(prescriptionDetails);
            return new ResponseEntity(HttpStatus.OK);
    }
}
