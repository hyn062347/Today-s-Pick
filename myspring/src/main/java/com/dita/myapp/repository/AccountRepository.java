package com.dita.myapp.repository;

import com.dita.myapp.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// import java.util.Optional;

// @Repository
// public interface AccountRepository extends JpaRepository<Account, String> {
//     Optional<Account> findByEmail(String email);
// }

public interface AccountRepository extends CrudRepository<Account, String>{

    @Modifying
    @Query("insert into Account(uid, password, uname, email) values(?1,?2,?3,?4)")
    public boolean signup(String uid, String password, String uname, String email);
}