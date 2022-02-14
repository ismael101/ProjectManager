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
import com.ismael.projects.projectmanagement.security.JWTproperties;
import com.ismael.projects.projectmanagement.security.MyUserDetails;
import com.ismael.projects.projectmanagement.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            Algorithm algorithm = Algorithm.HMAC512(JWTproperties.SECRET.getBytes());
            String token = JWT.create()
                    .withSubject(userDetails.getUsername())
                    .withClaim("authority", userDetails.getAuthorities().stream().toList().get(0).toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JWTproperties.EXPIRATION_TIME))
                    .sign(algorithm);
            System.out.println(userDetails.getAuthorities().toString());
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);

        }catch(BadCredentialsException b){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(b.getMessage());

        }catch(JWTCreationException j){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(j.getMessage());

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    public ResponseEntity signup(SignupRequest signupRequest){
        Optional<Register> registerOptional = registerRepository.findRegisterByEmail(signupRequest.getEmail());
        HashMap<String, String> message = new HashMap();
        if (registerOptional.isPresent()){
            Optional<Users> usersOptional = usersRepository.findByRegister(registerOptional.get().getId());
            if (usersOptional.isPresent()) {
                message.put("error","user already exists");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
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
