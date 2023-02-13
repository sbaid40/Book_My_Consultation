package com.upgrad.notificationservice.PrescriptionConsumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.notificationservice.Model.*;
import com.upgrad.notificationservice.Service.SesEmailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
public class PrescriptionConsumer {

    @Autowired
    SesEmailService sesEmailService;

    @Autowired
    RestTemplate restTemplate;

    @Value("http://USER-SERVICE")
    private String userServiceUrl;

    @Value("http://AUTH-SERVICE")
    private String authServiceUrl;

    @Value("http://DOCTOR-SERVICE")
    private String docServiceUrl;
    //Listen to Doctor service for prescription
    @KafkaListener(topics = "PRESCRIPTION")
    public void processDoctorApprovals(String content) throws IOException, TemplateException, MessagingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        Prescription prescription = mapper.readValue(content, new TypeReference<Prescription>() {
        });
        if(null != prescription){
            User user = this.getUserDetails(prescription.getUserId());
            Doctor doctor = this.getDoctorDetails(prescription.getDoctorId());
            if(null != user && null != doctor)
                sesEmailService.sendPrescriptionEmail(prescription, user, doctor);
        }
        System.out.println(content);
    }
    //method to get User's details
    private User getUserDetails(String userId) {
        String url = this.userServiceUrl + "/users/" + userId;
        String token = this.getToken("user", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.GET, entity, User.class);
        return response.getBody();
    }
    //method to get Doctor's details
    private Doctor getDoctorDetails(String doctorId) {
        String url = this.docServiceUrl + "/doctors/" + doctorId;
        String token = this.getToken("doctor", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Doctor> response = restTemplate.exchange(url, HttpMethod.GET, entity, Doctor.class);
        return response.getBody();
    }
    //method to get token for authentication and authorization
    private String getToken(String username, String password) {
        String url = this.authServiceUrl + "/oauth/token";
        UsernamePasswordModel request = new UsernamePasswordModel();
        request.setUsername(username);
        request.setPassword(password);
        HttpEntity<UsernamePasswordModel> requestEntity = new HttpEntity<>(request);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        return response.getBody();
    }
}
