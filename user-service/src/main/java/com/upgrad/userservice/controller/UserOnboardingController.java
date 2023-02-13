package com.upgrad.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.upgrad.userservice.model.ApplicationRole;
import com.upgrad.userservice.model.User;
import com.upgrad.userservice.repository.S3Repository;
import com.upgrad.userservice.service.MessageService;
import com.upgrad.userservice.service.UserOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserOnboardingController {

    @Autowired
    private UserOnboardingService userOnboardingService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private S3Repository s3Repository;

    //Endpoint to register a new user and send message to Kafka service
    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity onboardAUser(@Valid @RequestBody User user) throws IOException {
        User savedUser = this.userOnboardingService.saveUserDetails(user);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        this.messageService.produceMessage("USER_REGISTRATION", savedUser.getId(), mapper.writeValueAsString(savedUser));
        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }

    //Endpoint to upload the documents for user registration
    @PostMapping("/{userId}/document")
    public ResponseEntity uploadDocuments(@PathVariable("userId") String userId,@RequestParam MultipartFile[] files ) throws IOException {
        for(MultipartFile file: files) {
            s3Repository.uploadFiles(userId,file);
        }
        return new ResponseEntity("File(s) uploaded successfully", HttpStatus.CREATED);
    }
}
