package com.dita.myapp.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AccountsDto {

    private String uid;
    private String password;
    private String uname;
    private String email;
    private String uimg_src;
    private String uimg_name;
    private Timestamp created_at;

    private String[] preference;
}
