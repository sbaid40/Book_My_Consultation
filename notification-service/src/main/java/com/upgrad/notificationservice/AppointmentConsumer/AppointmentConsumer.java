package com.upgrad.notificationservice.AppointmentConsumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.notificationservice.Model.Appointment;
import com.upgrad.notificationservice.Model.Doctor;
import com.upgrad.notificationservice.Service.SesEmailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
public class AppointmentConsumer {

    @Autowired
    SesEmailService sesEmailService;

    //Listen to Appointment service
    @KafkaListener(topics = "APPOINTMENT_CONFIRMATION")
    public void processDoctorApprovals(String content) throws IOException, TemplateException, MessagingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        Appointment appointment = mapper.readValue(content, new TypeReference<Appointment>() {
        });
        if(null != appointment){
            sesEmailService.sendAppointmentEmail(appointment);
        }
        System.out.println(content);
    }
}
