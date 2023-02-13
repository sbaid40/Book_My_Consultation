package com.upgrad.doctorservice.service;

import com.upgrad.doctorservice.exception.RequestedResourceNotFoundException;
import com.upgrad.doctorservice.model.Doctor;
import com.upgrad.doctorservice.model.Status;
import com.upgrad.doctorservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorDataService {

    @Autowired
    private DoctorRepository doctorRepository;

    //method to find all of Doctors available
    public List<Doctor> getAllDoctorsData(){
        return this.doctorRepository.findAll();
    }

    //method to find Doctors data based on speciality and status
    public List<Doctor> getAllDoctorsBasedOnSpecialityAndStatus(String speciality, Status status) {
        return this.doctorRepository.findBySpecialityAndStatus(speciality,status);
    }
    //method to find Doctors data based on status
    public List<Doctor> getAllDoctorsBasedOnStatus(Status status) {
        return this.doctorRepository.findByStatus(status);
    }
    //method to find Doctors data based on speciality
    public List<Doctor> getAllDoctorsBasedOnSpeciality(String speciality) {
        return this.doctorRepository.findBySpeciality(speciality);
    }
    //method to find Doctors data based on doctor id
    public Doctor getDoctorDataById(String doctorId) {
        Optional<Doctor> doctor = this.doctorRepository.findById(doctorId);
        if(doctor.isPresent()){
            return doctor.get();
        }else{
            throw new RequestedResourceNotFoundException();
        }
    }
}
