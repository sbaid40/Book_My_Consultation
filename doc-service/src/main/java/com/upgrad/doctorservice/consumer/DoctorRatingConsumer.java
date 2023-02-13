package com.upgrad.doctorservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.doctorservice.model.Doctor;
import com.upgrad.doctorservice.model.DoctorRating;
import com.upgrad.doctorservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DoctorRatingConsumer {

    @Autowired
    DoctorRepository doctorRepository;

    @KafkaListener(topics = "DOCTOR_RATING")
    public void processDoctorRatings(String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        DoctorRating receivedDoctor = mapper.readValue(content, new TypeReference<DoctorRating>() {
        });
        if(null != receivedDoctor){
            Optional<Doctor> doctorInDb = this.doctorRepository.findById(receivedDoctor.getDoctorId());
            if(doctorInDb.isPresent()){
                Doctor doctor = doctorInDb.get();
                doctor.setRating(String.valueOf(receivedDoctor.getRating()));
                this.doctorRepository.save(doctor);
            }

        }
        System.out.println(content);

    }
}
