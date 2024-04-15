package com.example.t2207a_springboot.controller;

import com.example.t2207a_springboot.dto.request_model.RegisterUser;
import com.example.t2207a_springboot.entity.User;
import com.example.t2207a_springboot.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<User> response(@RequestBody RegisterUser registerUser){
        User user = authenticationService.signup(registerUser);
        return  ResponseEntity.ok(user);
    }
}
