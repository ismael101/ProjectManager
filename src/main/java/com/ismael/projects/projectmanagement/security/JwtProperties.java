package com.ismael.projects.projectmanagement.security;

public class JwtProperties {
    public static final String SECRET = "idwniu39un293ucn9wduh9csdc";
    public static final int EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}