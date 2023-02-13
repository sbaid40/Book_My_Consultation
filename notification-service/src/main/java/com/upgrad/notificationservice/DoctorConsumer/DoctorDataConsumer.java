package com.upgrad.notificationservice.DoctorConsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.notificationservice.Model.Doctor;
import com.upgrad.notificationservice.Service.SesEmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DoctorDataConsumer {
    @Autowired
    SesEmailVerificationService sesEmailVerificationService;

    //Listen to Doctor service
    @KafkaListener(topics = "DOCTOR_REGISTRATION")
    public void processDoctorRejections(String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        Doctor receivedDoctor = mapper.readValue(content, new TypeReference<Doctor>() {
        });
        if(null != receivedDoctor){
            sesEmailVerificationService.verifyEmail(receivedDoctor.getEmailId());
        }
        System.out.println(content);

    }
}
