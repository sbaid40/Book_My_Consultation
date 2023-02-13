package com.upgrad.doctorservice.service;

import com.upgrad.doctorservice.exception.RequestedResourceNotFoundException;
import com.upgrad.doctorservice.model.ApproveRequest;
import com.upgrad.doctorservice.model.Doctor;
import com.upgrad.doctorservice.model.Status;
import com.upgrad.doctorservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DoctorApprovalService {

    @Autowired
    private DoctorRepository doctorRepository;

    //method to approve doctors registration
    public Doctor approveDoctorRegistion(String doctorId, ApproveRequest approveRequest) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if(doctor.isPresent()){
            Doctor doctorInDatabase = doctor.get();
            doctorInDatabase.setStatus(Status.ACTIVE);
            doctorInDatabase.setApprovedBy(approveRequest.getApprovedBy());
            doctorInDatabase.setApproverComments(approveRequest.getApproverComments());
            doctorInDatabase.setVerificationDate(LocalDate.now());
            return this.doctorRepository.save(doctorInDatabase);
        }else{
            throw new RequestedResourceNotFoundException();
        }
    }
    //method to reject doctors registration request
    public Doctor rejectDoctorRegistration(String doctorId, ApproveRequest approveRequest) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if(doctor.isPresent()){
            Doctor doctorInDatabase = doctor.get();
            doctorInDatabase.setStatus(Status.REJECTED);
            doctorInDatabase.setApprovedBy(approveRequest.getApprovedBy());
            doctorInDatabase.setApproverComments(approveRequest.getApproverComments());
            doctorInDatabase.setVerificationDate(LocalDate.now());
            return this.doctorRepository.save(doctorInDatabase);
        }else{
            throw new RequestedResourceNotFoundException();
        }
    }
}
