package com.ismael.projects.projectmanagement.controller;

import com.ismael.projects.projectmanagement.requests.SignupRequest;
import com.ismael.projects.projectmanagement.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth/")
public class AuthController {
    private AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public AuthController() {
    }

    public ResponseEntity signup(@RequestBody SignupRequest signupRequest){
        ResponseEntity response = this.authenticationService.signup(signupRequest);
        return response;
    }

}
