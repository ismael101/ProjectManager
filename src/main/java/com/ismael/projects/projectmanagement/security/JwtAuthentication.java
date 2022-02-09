package com.ismael.projects.projectmanagement.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismael.projects.projectmanagement.requests.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class JwtAuthentication extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequest loginRequest = null;
        try{
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
        }catch(IOException e){
            e.printStackTrace();
        }
        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        // Authenticate user
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        MyUserDetails userDetails = (MyUserDetails) authResult.getPrincipal();
        try{
            Algorithm algorithm = Algorithm.HMAC512(JwtProperties.SECRET.getBytes());
            String token = JWT.create()
                    .withSubject(userDetails.getUsername())
                    .withClaim("authorities", Arrays.asList(userDetails.getAuthorities()))
                    .withClaim("team", userDetails.getTeam())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                    .sign(algorithm);
            response.addHeader(JwtProperties.HEADER_STRING,
                    JwtProperties.TOKEN_PREFIX + token);

        }catch(JWTCreationException e){
            throw new IllegalStateException("error creating jwt token");
        }


    }

}
