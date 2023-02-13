package com.upgrad.auth.controller;

import com.upgrad.auth.model.UsernamePasswordModel;
import com.upgrad.auth.service.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
public class TokenController {

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    //End point to fetch the token of the User using username and password
    @PostMapping("/token")
    public ResponseEntity generateToken(@RequestBody UsernamePasswordModel loginCredentials){
        return new ResponseEntity(jwtTokenUtils.generateToken(loginCredentials),
                HttpStatus.OK);
    }

}
