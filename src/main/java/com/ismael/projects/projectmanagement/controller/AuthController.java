package com.ismael.projects.projectmanagement.controller;

import com.ismael.projects.projectmanagement.requests.LoginRequest;
import com.ismael.projects.projectmanagement.requests.SignupRequest;
import com.ismael.projects.projectmanagement.requests.VerificationRequest;
import com.ismael.projects.projectmanagement.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth/")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws Exception {
        ResponseEntity response = this.authenticationService.login(loginRequest);
        return response;
    }

    @PostMapping(path = "/signup")
    public ResponseEntity signup(@RequestBody SignupRequest signupRequest){
        ResponseEntity response = this.authenticationService.signup(signupRequest);
        return response;
    }

    @PostMapping(path = "/verfiy")
    public ResponseEntity verfiy(@RequestBody VerificationRequest verificationRequest){

        return null;
    }

}
