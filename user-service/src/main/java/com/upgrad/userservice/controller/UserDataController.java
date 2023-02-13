package com.upgrad.userservice.controller;

import com.upgrad.userservice.exception.RequestedResourceNotFoundException;
import com.upgrad.userservice.model.ErrorModel;
import com.upgrad.userservice.model.User;
import com.upgrad.userservice.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserDataController {

    @Autowired
    UserDataService userDataService;

    //End point to get User data using user ID
    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity getUserData(@PathVariable("userId") String userId){
        User user = this.userDataService.findUserById(userId);
            return new ResponseEntity(user, HttpStatus.OK);

    }

    @ExceptionHandler(RequestedResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorModel handleRequestedResourceNotFoundException(){
        return ErrorModel.builder().errorCode("ERR_USER_001").errorMessage("User Not Found").build();
    }
}
