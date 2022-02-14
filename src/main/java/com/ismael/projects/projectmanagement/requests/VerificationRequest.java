package com.ismael.projects.projectmanagement.requests;

public class VerificationRequest {
    String token;

    public VerificationRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
