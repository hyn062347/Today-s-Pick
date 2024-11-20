package com.dita.myapp.controller;

import com.dita.myapp.dto.LoginRequest;
import com.dita.myapp.dto.LoginResponse;
import com.dita.myapp.dto.SignupRequest;
import com.dita.myapp.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        accountService.signup(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return accountService.login(request)
                .map(account -> ResponseEntity.ok(new LoginResponse(
                        account.getUid(),
                        account.getUname(),
                        "fake-jwt-token" // JWT 토큰 구현 시 수정 필요
                )))
                .orElse(ResponseEntity.status(401).body("로그인 실패"));
    }
}
