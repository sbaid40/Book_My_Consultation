package com.upgrad.doctorservice.repository;

import com.upgrad.doctorservice.model.Doctor;
import com.upgrad.doctorservice.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor,String> {
    @Query("{'speciality' : ?0, 'status': ?1}")
    public List<Doctor> findBySpecialityAndStatus(String speciality, Status status);
    @Query("{'speciality' : ?0}")
    public List<Doctor> findBySpeciality(String speciality);
    @Query("{'status' : ?0}")
    public List<Doctor> findByStatus(Status status);


}
