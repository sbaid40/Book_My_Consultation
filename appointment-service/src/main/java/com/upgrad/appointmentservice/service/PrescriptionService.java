package com.upgrad.appointmentservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.appointmentservice.entity.Appointment;
import com.upgrad.appointmentservice.exception.PaymentIncompleteException;
import com.upgrad.appointmentservice.model.Prescription;
import com.upgrad.appointmentservice.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PrescriptionService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private AppointmentService appointmentService;

    //method to save Prescription details in database
    public void savePrescription(Prescription prescription) throws IOException {
        Appointment appointment = this.appointmentService.getAppointmentDetails(prescription.getAppointmentId());
        if(appointment.getStatus().equalsIgnoreCase("Confirmed")) {
            this.prescriptionRepository.save(prescription);
            this.sendPatientPrescription(prescription);
        }else{
            throw new PaymentIncompleteException();
        }
    }

    //method to send Prescription details to Kafka that sends an email to user
    public void sendPatientPrescription(Prescription prescriptionDetails) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        this.messageService.produceMessage("PRESCRIPTION", prescriptionDetails.getId(), mapper.writeValueAsString(prescriptionDetails));
    }
}
