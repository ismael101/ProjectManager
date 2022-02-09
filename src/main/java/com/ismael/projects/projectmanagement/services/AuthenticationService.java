package com.ismael.projects.projectmanagement.services;

import com.ismael.projects.projectmanagement.repositories.RegisterRepository;
import com.ismael.projects.projectmanagement.repositories.UsersRepository;
import com.ismael.projects.projectmanagement.requests.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private UsersRepository usersRepository;
    private RegisterRepository registerRepository;

    public AuthenticationService() {
    }

    public ResponseEntity signup(SignupRequest signupRequest){

        return null;
    }
}
