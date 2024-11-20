package com.dita.myapp.dto;

import lombok.Data;

@Data
public class AccountDto {

    private Long uid;
    private String password;
    private String uname;
    private String email;
}
