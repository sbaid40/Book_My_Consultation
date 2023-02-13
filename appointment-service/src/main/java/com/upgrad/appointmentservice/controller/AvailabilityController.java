package com.upgrad.appointmentservice.controller;

import com.upgrad.appointmentservice.dto.AvailabilityData;
import com.upgrad.appointmentservice.service.AvailabilityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class AvailabilityController {
    private AvailabilityService availabilityService;
    private ModelMapper modelMapper;
    @Autowired
    public AvailabilityController(AvailabilityService availabilityService, ModelMapper modelMapper){
        this.availabilityService = availabilityService;
        this.modelMapper=modelMapper;
    }
    //End point to update the availability of doctor using Doctor ID
    @PostMapping("/{doctorId}/availability")
    public ResponseEntity updateDoctorAvailability(@PathVariable("doctorId") String doctorId, @RequestBody AvailabilityData availabilityData){
        this.availabilityService.updateDoctorsAvailability(doctorId, availabilityData);
        return new ResponseEntity(HttpStatus.OK);
    }

    //End point to get the details of Doctors availability
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity getDoctorsAvailabilityData(@PathVariable("doctorId") String doctorId){
        AvailabilityData doctorsAvailabilityData = availabilityService.getDoctorsAvailablityData(doctorId);
        return new ResponseEntity(doctorsAvailabilityData, HttpStatus.OK);
    }
}
