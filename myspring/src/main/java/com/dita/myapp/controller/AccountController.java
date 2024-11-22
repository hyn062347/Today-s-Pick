package com.dita.myapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dita.myapp.domain.Account;
import com.dita.myapp.dto.AccountDto;
import com.dita.myapp.dto.SignInDto;
import com.dita.myapp.service.AccountService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
@Log
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto) {
        try{
            int i = accountService.createAccount(accountDto);
            if(i>=1){
                throw new SQLException("Invalid ID or email");
            }
        }
        catch(Exception e){
            return ResponseEntity.ok().body(false);
        }

        return ResponseEntity.ok().body(true);
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
        Account account;
        try {
            account = accountService.signIn(signInDto.getUid(), signInDto.getPassword());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.toString());
        }
        
        SignInDto dto=new SignInDto();
        dto.setUid(account.getUid());
        return ResponseEntity.ok().body(dto);
    }
    

}
