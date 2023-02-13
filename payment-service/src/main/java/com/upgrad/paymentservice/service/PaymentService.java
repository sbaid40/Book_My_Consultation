package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.dto.PaymentDetails;
import com.upgrad.paymentservice.model.UsernamePasswordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {
    @Value("http://APPOINTMENT-SERVICE")
    private String appointmentServiceUrl;

    @Value("http://AUTH-SERVICE")
    private String authServiceUrl;

    @Autowired
    RestTemplate restTemplate;
    //method to receive payment for appointment
    public void receivePayment(String appointmentId) {
        String url = appointmentServiceUrl + "/payment/"+ appointmentId;
        String token = this.getToken("user", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        this.restTemplate.postForEntity(url, entity,String.class);
    }
    //method to get token
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
