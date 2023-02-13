package com.upgrad.appointmentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.appointmentservice.dto.AppointmentDetails;
import com.upgrad.appointmentservice.dto.AppointmentRequest;
import com.upgrad.appointmentservice.entity.Appointment;
import com.upgrad.appointmentservice.service.AppointmentService;
import com.upgrad.appointmentservice.service.AvailabilityService;
import com.upgrad.appointmentservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private MessageService messageService;

    //End point to create an appointment
    @PostMapping("")
    public ResponseEntity makeDoctorAppointment(@RequestBody AppointmentRequest appointmentRequest) throws IOException {
        Appointment appointment = this.appointmentService.makeAppointment(appointmentRequest);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        this.messageService.produceMessage("APPOINTMENT_CONFIRMATION", appointment.getAppointmentId(), mapper.writeValueAsString(appointment));
        return new ResponseEntity(appointment.getAppointmentId(), HttpStatus.OK);
    }

    //End point to find appointment details using appointment ID
    @GetMapping("/{appointmentId}")
    public ResponseEntity getAppointmentDetails(@PathVariable("appointmentId") String appointmentId){
        Appointment appointmentDetails = this.appointmentService.getAppointmentDetails(appointmentId);
        return new ResponseEntity(appointmentDetails, HttpStatus.OK);
    }

}
