package com.dita.myapp.dto;

public class LoginRequest {

    private String uid;
    private String password;

    // Default constructor
    public LoginRequest() {
    }

    // Constructor with parameters
    public LoginRequest(String uid, String password) {
        this.uid = uid;
        this.password = password;
    }

    // Getters and Setters
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}