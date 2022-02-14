package com.ismael.projects.projectmanagement.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTverification extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JWTproperties.TOKEN_PREFIX);
        if(header == null || !header.startsWith(JWTproperties.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(JWTproperties.TOKEN_PREFIX, "");
        try{
            Algorithm algorithm = Algorithm.HMAC256(JWTproperties.SECRET.getBytes());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    jwt.getSubject(),
                    jwt.getClaims()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

        }catch(JWTVerificationException e){
            throw new IllegalStateException("token is invalid");
        }

    }

}
