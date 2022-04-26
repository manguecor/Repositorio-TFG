package com.tfg.apuesta.user;

public class AuthenticationResponse {
    private String token;
    private String username;

    public AuthenticationResponse(String token, User user){
        this.username = user.getUsername();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
