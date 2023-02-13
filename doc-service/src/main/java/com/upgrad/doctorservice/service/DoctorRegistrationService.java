package com.upgrad.doctorservice.service;

import com.upgrad.doctorservice.model.Doctor;
import com.upgrad.doctorservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorRegistrationService {

    @Autowired
    private DoctorRepository doctorRepository;

    //method to save Doctor's registration data
    public Doctor saveDoctorRegistrationData(Doctor doctor) {
        return doctorRepository.insert(doctor);
    }
}
