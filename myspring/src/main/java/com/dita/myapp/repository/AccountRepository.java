package com.dita.myapp.repository;

import com.dita.myapp.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.Optional;

// @Repository
// public interface AccountRepository extends JpaRepository<Account, String> {
//     Optional<Account> findByEmail(String email);
// }

public interface AccountRepository extends JpaRepository<Account, String>{

}