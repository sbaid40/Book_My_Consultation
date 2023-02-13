package com.upgrad.notificationservice.DoctorConsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.notificationservice.Model.Doctor;
import com.upgrad.notificationservice.Service.SesEmailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
public class DoctorApprovalConsumer {

    @Autowired
    SesEmailService sesEmailService;
    //Listen to Doctor service
    @KafkaListener(topics = "DOCTOR_APPROVAL")
    public void processDoctorApprovals(String content) throws IOException, TemplateException, MessagingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        Doctor receivedDoctor = mapper.readValue(content, new TypeReference<Doctor>() {
        });
        if(null != receivedDoctor){
            sesEmailService.sendApprovalEmail(receivedDoctor);
        }
        System.out.println(content);
    }

    @KafkaListener(topics = "DOCTOR_REJECTION")
    public void processDoctorRejections(String content) throws IOException, TemplateException, MessagingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        Doctor receivedDoctor = mapper.readValue(content, new TypeReference<Doctor>() {
        });
        if(null != receivedDoctor){
            sesEmailService.sendRejectionEmail(receivedDoctor);
        }
        System.out.println(content);
    }
}
