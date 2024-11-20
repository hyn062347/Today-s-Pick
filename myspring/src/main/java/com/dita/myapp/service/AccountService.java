package com.dita.myapp.service;

// import com.dita.myapp.dto.LoginRequest;
// import com.dita.myapp.dto.SignupRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.dita.myapp.domain.Account;
import com.dita.myapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

// import java.util.Optional;

// @Service
// public class AccountService {

//     private final AccountRepository accountRepository;
//     private final PasswordEncoder passwordEncoder;

//     public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
//         this.accountRepository = accountRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     // 회원가입 처리
//     public Account signup(SignupRequest request) {
//         String encodedPassword = passwordEncoder.encode(request.getPassword());
//         Account account = new Account();
//         account.setUid(request.getUid());
//         account.setPassword(encodedPassword);
//         account.setUname(request.getUname());
//         account.setEmail(request.getEmail());
//         return accountRepository.save(account);
//     }

//     // 로그인 처리
//     public Optional<Account> login(LoginRequest request) {
//         Optional<Account> account = accountRepository.findById(request.getUid());
//         if (account.isPresent() && passwordEncoder.matches(request.getPassword(), account.get().getPassword())) {
//             return account;
//         }
//         return Optional.empty();
//     }
// }


@RequiredArgsConstructor
@Service
public class AccountService{
    //오류 일어나면 초기화 없어서 오류나는걸수도 있음
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account createAccount(Long uid, String uname, String password, String email){
        Account account = new Account();
        account.setUid(uid);
        account.setName(uname);
        account.setPassword(passwordEncoder.encode(password));
        account.setEmail(email);

        this.accountRepository.save(account);
        return account;
    }

    public Account signIn(Long uid, String password){
        Account account = accountRepository.findById(uid).orElseThrow(()-> new IllegalArgumentException("Invalid UID or password"));

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new IllegalArgumentException("Invalid UID or password");
        }
        return account;
    }
}
