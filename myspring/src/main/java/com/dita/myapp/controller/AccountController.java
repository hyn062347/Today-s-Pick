package com.dita.myapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dita.myapp.domain.Account;
import com.dita.myapp.dto.AccountDto;
import com.dita.myapp.dto.SignInDto;
import com.dita.myapp.service.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto) {
        Account account = accountService.createAccount(
            accountDto.getUid(),
            accountDto.getUname(),
            accountDto.getPassword(),
            accountDto.getEmail()
            );

        return ResponseEntity.ok(account);
    }
    
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInDto signInDto) {
        try {
            Account account = accountService.signIn(signInDto.getUid(), signInDto.getPassword());
            return ResponseEntity.ok("Welcome " + account.getName());
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid UID or password");
        }
    }
    

}
