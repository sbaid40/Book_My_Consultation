package com.upgrad.appointmentservice.service;

import com.amazonaws.services.fms.model.App;
import com.upgrad.appointmentservice.dto.AppointmentDetails;
import com.upgrad.appointmentservice.dto.AppointmentRequest;
import com.upgrad.appointmentservice.entity.Appointment;
import com.upgrad.appointmentservice.exception.RequestedResourceNotFoundException;
import com.upgrad.appointmentservice.model.Doctor;
import com.upgrad.appointmentservice.model.PaymentStatus;
import com.upgrad.appointmentservice.model.User;
import com.upgrad.appointmentservice.model.UsernamePasswordModel;
import com.upgrad.appointmentservice.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Value("http://USER-SERVICE")
    private String userServiceUrl;

    @Value("http://AUTH-SERVICE")
    private String authServiceUrl;

    @Value("http://DOCTOR-SERVICE")
    private String docServiceUrl;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RestTemplate restTemplate;

    //method to create an appointment for the user
    public Appointment makeAppointment(AppointmentRequest appointmentRequest) {
        User userDetails = this.getUserDetails(appointmentRequest.getUserId());
        Doctor doctorDetails = this.getDoctorDetails(appointmentRequest.getDoctorId());
        if(null != userDetails && null != doctorDetails) {
            Appointment appointment = Appointment.builder()
                    .appointmentDate(LocalDate.parse(appointmentRequest.getAppointmentDate()))
                    .createdDate(LocalDateTime.now())
                    .doctorId(appointmentRequest.getDoctorId())
                    .doctorName(doctorDetails.getFirstName())
                    .status(PaymentStatus.PENDING_PAYMENT.name())
                    .userId(appointmentRequest.getUserId())
                    .userEmailId(userDetails.getEmailId())
                    .userName(userDetails.getFirstName())
                    .build();

            return appointmentRepository.save(appointment);
        }else{
            throw new RequestedResourceNotFoundException();
        }
    }

    //method to get the details of an existing doctor using doctor id
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

    //method to get existing user using user id
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

    //method to get authentication token
    private String getToken(String username, String password) {
        String url = this.authServiceUrl + "/oauth/token";
        UsernamePasswordModel request = new UsernamePasswordModel();
        request.setUsername(username);
        request.setPassword(password);
        HttpEntity<UsernamePasswordModel> requestEntity = new HttpEntity<>(request);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        return response.getBody();
    }
    //method to  appointment details from database using appintment ID
    public Appointment getAppointmentDetails(String appointmentId) {
        return appointmentRepository.findByAppointmentId(appointmentId);
    }
    //method to  appointment details from database using user ID
    public List<Appointment> getUserAppointmentDetails(String userId) {
        return this.appointmentRepository.findAppointmentByUserId(userId);
    }
    //method to update Payment status
    public void updatePaymentStatus(String appintmentId) {
        Appointment appointment = this.appointmentRepository.findByAppointmentId(appintmentId);
        if(null != appointment){
            appointment.setStatus(PaymentStatus.CONFIRMED.name());
            this.appointmentRepository.save(appointment);
        }else {
            throw new RequestedResourceNotFoundException();
        }
    }
}
