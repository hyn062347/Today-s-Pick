package com.dita.myapp.service;

import com.dita.myapp.domain.Account;
import com.dita.myapp.dto.LoginRequest;
import com.dita.myapp.dto.SignupRequest;
import com.dita.myapp.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 처리
    public Account signup(SignupRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Account account = new Account();
        account.setUid(request.getUid());
        account.setPassword(encodedPassword);
        account.setUname(request.getUname());
        account.setEmail(request.getEmail());
        return accountRepository.save(account);
    }

    // 로그인 처리
    public Optional<Account> login(LoginRequest request) {
        Optional<Account> account = accountRepository.findById(request.getUid());
        if (account.isPresent() && passwordEncoder.matches(request.getPassword(), account.get().getPassword())) {
            return account;
        }
        return Optional.empty();
    }
}
