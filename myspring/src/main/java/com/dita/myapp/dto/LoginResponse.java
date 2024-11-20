package com.dita.myapp.dto;

public class LoginResponse {

    private String uid;
    private String uname;
    private String token;

    // Default constructor
    public LoginResponse() {
    }

    // Constructor with parameters
    public LoginResponse(String uid, String uname, String token) {
        this.uid = uid;
        this.uname = uname;
        this.token = token;
    }

    // Getters and Setters
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}