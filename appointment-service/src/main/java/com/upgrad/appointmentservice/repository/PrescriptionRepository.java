package com.upgrad.appointmentservice.repository;

import com.upgrad.appointmentservice.model.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrescriptionRepository extends MongoRepository<Prescription,String> {
}
