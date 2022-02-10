package com.ismael.projects.projectmanagement.services;

import com.ismael.projects.projectmanagement.models.Register;
import com.ismael.projects.projectmanagement.models.Users;
import com.ismael.projects.projectmanagement.repositories.RegisterRepository;
import com.ismael.projects.projectmanagement.repositories.UsersRepository;
import com.ismael.projects.projectmanagement.requests.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private UsersRepository usersRepository;
    private RegisterRepository registerRepository;
    private PasswordEncoder passwordEncoder;

    public AuthenticationService(UsersRepository usersRepository, RegisterRepository registerRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.registerRepository = registerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity signup(SignupRequest signupRequest){
        Optional<Register> registerOptional = registerRepository.findRegisterByEmail(signupRequest.getEmail());
        if (registerOptional.isPresent()){
            Optional<Users> usersOptional = usersRepository.findByRegsiter(registerOptional.get().getId());
            if (usersOptional.isPresent()) {
                throw new IllegalStateException("users already exists");
            }else{
                Register register = this.registerRepository.getById(registerOptional.get().getId());
                Users user = new Users(signupRequest.getUsername(), this.passwordEncoder.encode(signupRequest.getPassword()), register);
                this.usersRepository.save(user);

            }

        }else{
            throw new IllegalStateException("email isn't registered");
        }

        return null;
    }
}
