package com.upgrad.doctorservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.doctorservice.model.Doctor;
import com.upgrad.doctorservice.repository.S3Repository;
import com.upgrad.doctorservice.service.DoctorRegistrationService;
import com.upgrad.doctorservice.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/doctors")
public class DoctorRegistrationController {
    private DoctorRegistrationService doctorRegistrationService;
    private MessageService messageService;
    private S3Repository s3Repository;

    @Autowired
    public DoctorRegistrationController(DoctorRegistrationService doctorRegistrationService, MessageService messageService, S3Repository s3Repository){
        this.doctorRegistrationService = doctorRegistrationService;
        this.messageService = messageService;
        this.s3Repository = s3Repository;
    }

    //End point to register a new Doctor
    @PostMapping
    public ResponseEntity doctorRegistration(@Valid  @RequestBody Doctor doctor) throws IOException {
        Doctor savedDoctorInfo = this.doctorRegistrationService.saveDoctorRegistrationData(doctor);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        this.messageService.produceMessage("DOCTOR_REGISTRATION", savedDoctorInfo.getId(), mapper.writeValueAsString(savedDoctorInfo));
        return new ResponseEntity(doctor, HttpStatus.CREATED);
    }

    //End point to upload registration documents by the User
    @PostMapping("/{doctorId}/document")
    public ResponseEntity uploadDocuments(@PathVariable("doctorId") String doctorId,@RequestParam MultipartFile[] files ) throws IOException {
        for(MultipartFile file: files) {
            s3Repository.uploadFiles(doctorId,file);
        }
        return new ResponseEntity("File(s) uploaded successfully", HttpStatus.CREATED);
    }
}
