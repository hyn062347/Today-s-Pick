package com.dita.myapp.dto;

public class SignupRequest {

    private String uid;
    private String password;
    private String uname;
    private String email;

    // Default Constructor
    public SignupRequest() {
    }

    // Parameterized Constructor (Optional)
    public SignupRequest(String uid, String password, String uname, String email) {
        this.uid = uid;
        this.password = password;
        this.uname = uname;
        this.email = email;
    }

    // Getters
    public String getUid() {
        return uid;
    }

    public String getPassword() {
        return password;
    }

    public String getUname() {
        return uname;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
