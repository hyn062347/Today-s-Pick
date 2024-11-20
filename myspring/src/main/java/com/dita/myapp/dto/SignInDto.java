package com.dita.myapp.dto;

import lombok.Data;

@Data
public class SignInDto {

    private Long uid;
    private String password;
}