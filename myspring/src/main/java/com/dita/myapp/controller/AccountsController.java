package com.dita.myapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dita.myapp.domain.Accounts;
import com.dita.myapp.dto.AccountsDto;
import com.dita.myapp.dto.SignInDto;
import com.dita.myapp.service.AccountsService;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
@Log
public class AccountsController {

    private final AccountsService accountsService;

    @PostMapping("/signup")
    public ResponseEntity<?> createAccount(@RequestBody AccountsDto accountsDto) {
        try{
            int i=accountsService.createAccount(accountsDto);
            if(i>=1)
                throw new SQLException("Invalid UID or email");
        }
        catch(Exception e){
            return ResponseEntity.ok().body(false);
        }

        return ResponseEntity.ok().body(true);
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
        Accounts account;
        try {
            account = accountsService.signIn(signInDto.getUid(), signInDto.getPassword());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.toString());
        }
        
        SignInDto dto=new SignInDto();
        dto.setUid(account.getUid());
        System.out.println(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/getuser")
    public ResponseEntity<?> getUser(@RequestBody AccountsDto accountdto) {
        AccountsDto accdto=new AccountsDto();

        try {
            accdto = accountsService.getUserInfo(accountdto.getUid());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.toString());
        }
        
        return ResponseEntity.ok().body(accdto);
    }
    

}
