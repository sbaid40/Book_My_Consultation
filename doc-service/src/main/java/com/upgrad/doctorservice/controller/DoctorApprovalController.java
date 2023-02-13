package com.upgrad.doctorservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.doctorservice.exception.RequestedResourceNotFoundException;
import com.upgrad.doctorservice.model.ApproveRequest;
import com.upgrad.doctorservice.model.Doctor;
import com.upgrad.doctorservice.model.ErrorModel;
import com.upgrad.doctorservice.service.DoctorApprovalService;
import com.upgrad.doctorservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/doctors")
public class DoctorApprovalController {

    private DoctorApprovalService doctorApprovalService;
    private MessageService messageService;

    @Autowired
    public DoctorApprovalController(DoctorApprovalService doctorApprovalService, MessageService messageService){
        this.doctorApprovalService = doctorApprovalService;
        this.messageService = messageService;

    }

    //End point to approve the Doctor request using doctor ID by Admin
    @PutMapping("/{doctorId}/approve")
    public ResponseEntity approveDoctorRegistration(@PathVariable("doctorId") String doctorId,@Valid @RequestBody ApproveRequest approveRequest) throws IOException {
        Doctor approvedDoctor = doctorApprovalService.approveDoctorRegistion(doctorId, approveRequest);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        this.messageService.produceMessage("DOCTOR_APPROVAL", approvedDoctor.getId(), mapper.writeValueAsString(approvedDoctor));
        return new ResponseEntity(HttpStatus.OK);
    }

    //End point to Reject the Doctor request using Doctor ID
    @PutMapping("/{doctorId}/reject")
    public ResponseEntity rejectDoctorRegistration(@PathVariable("doctorId") String doctorId,@RequestBody ApproveRequest approveRequest) throws IOException {
        Doctor rejectedDoctor = doctorApprovalService.rejectDoctorRegistration(doctorId, approveRequest);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        this.messageService.produceMessage("DOCTOR_REJECTION", rejectedDoctor.getId(), mapper.writeValueAsString(rejectedDoctor));
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(RequestedResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorModel handleRequestedResourceNotFoundException(){
        return ErrorModel.builder().errorCode("ERR_DOCTOR_001").errorMessage("Doctor Not Found").build();
    }
}
