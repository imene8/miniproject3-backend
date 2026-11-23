package com.chaussures.backend.dto;

import java.util.List;

public class LoginResponse {

    private final String token;
    private final String username;
    private final List<String> roles;

    public LoginResponse(String token, String username, List<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
