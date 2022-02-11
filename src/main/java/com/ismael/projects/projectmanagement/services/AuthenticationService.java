package com.ismael.projects.projectmanagement.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.ismael.projects.projectmanagement.models.Register;
import com.ismael.projects.projectmanagement.models.Users;
import com.ismael.projects.projectmanagement.repositories.RegisterRepository;
import com.ismael.projects.projectmanagement.repositories.UsersRepository;
import com.ismael.projects.projectmanagement.requests.LoginRequest;
import com.ismael.projects.projectmanagement.requests.SignupRequest;
import com.ismael.projects.projectmanagement.security.JwtProperties;
import com.ismael.projects.projectmanagement.security.MyUserDetails;
import com.ismael.projects.projectmanagement.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UsersRepository usersRepository;
    private final RegisterRepository registerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public AuthenticationService(UsersRepository usersRepository, RegisterRepository registerRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService) {
        this.usersRepository = usersRepository;
        this.registerRepository = registerRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public ResponseEntity login(LoginRequest loginRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(loginRequest.getUsername());
            Algorithm algorithm = Algorithm.HMAC512(JwtProperties.SECRET.getBytes());
            String token = JWT.create()
                    .withSubject(userDetails.getUsername())
                    .withClaim("authority", userDetails.getAuthorities().stream().toList().get(0).toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                    .sign(algorithm);
            System.out.println(userDetails.getAuthorities().toString());
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            return ResponseEntity.ok(map);

        }catch(BadCredentialsException b){
            return ResponseEntity.ok(b.getMessage());

        }catch(JWTCreationException j){
            return ResponseEntity.ok(j.getMessage());

        }catch(Exception e){
            return ResponseEntity.ok(e.getMessage());
        }

    }

    public ResponseEntity signup(SignupRequest signupRequest){
        Optional<Register> registerOptional = registerRepository.findRegisterByEmail(signupRequest.getEmail());
        if (registerOptional.isPresent()){
            Optional<Users> usersOptional = usersRepository.findByRegister(registerOptional.get().getId());
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
