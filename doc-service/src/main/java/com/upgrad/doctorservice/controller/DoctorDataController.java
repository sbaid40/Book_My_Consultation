package com.upgrad.doctorservice.controller;

import com.upgrad.doctorservice.model.Doctor;
import com.upgrad.doctorservice.model.Status;
import com.upgrad.doctorservice.service.DoctorDataService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorDataController {

    private DoctorDataService doctorDataService;
    @Autowired
    public DoctorDataController(DoctorDataService doctorDataService){
        this.doctorDataService = doctorDataService;

    }

    //End point to get Doctor's information using parameters like status and speciality
    @GetMapping("")
    public ResponseEntity getDoctorsInformation(@RequestParam(required = false)Status status, @RequestParam(required = false)String speciality) {
        if (ObjectUtils.allNull(status, speciality)) {
            List<Doctor> listOfDoctors = this.doctorDataService.getAllDoctorsData();
            return new ResponseEntity(listOfDoctors, HttpStatus.OK);
        } else if (null != status && null != speciality) {
            List<Doctor> listOfDoctors = this.doctorDataService.getAllDoctorsBasedOnSpecialityAndStatus(speciality,status);
            return new ResponseEntity(listOfDoctors, HttpStatus.OK);
        } else if(null != status && null == speciality) {
            List<Doctor> listOfDoctors = this.doctorDataService.getAllDoctorsBasedOnStatus(status);
            return new ResponseEntity(listOfDoctors, HttpStatus.OK);
        } else if(null == status && null != speciality){
            List<Doctor> listOfDoctors = this.doctorDataService.getAllDoctorsBasedOnSpeciality(speciality);
            return new ResponseEntity(listOfDoctors, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //End point to get Doctor's information using Doctor ID
    @GetMapping("/{doctorId}")
    public ResponseEntity getDoctorsInformation( @PathVariable("doctorId") String doctorId){
        Doctor doctor = this.doctorDataService.getDoctorDataById(doctorId);
        return new ResponseEntity(doctor, HttpStatus.OK);
    }
}
