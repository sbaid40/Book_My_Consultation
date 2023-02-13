package com.upgrad.notificationservice.UserConsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.notificationservice.Model.Doctor;
import com.upgrad.notificationservice.Model.User;
import com.upgrad.notificationservice.Service.SesEmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class UserDataConsumer {

    @Autowired
    SesEmailVerificationService sesEmailVerificationService;

    //method to listen to User service
    @KafkaListener(topics = "USER_REGISTRATION")
    public void processUserRegistration(String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        User receivedUser = mapper.readValue(content, new TypeReference<User>() {
        });
        if(null != receivedUser){
            sesEmailVerificationService.verifyEmail(receivedUser.getEmailId());
        }
        System.out.println(content);

    }
}
