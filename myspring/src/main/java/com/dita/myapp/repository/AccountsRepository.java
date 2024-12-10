package com.dita.myapp.repository;

import com.dita.myapp.domain.Accounts;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

// import java.util.Optional;

// @Repository
// public interface AccountRepository extends JpaRepository<Account, String> {
//     Optional<Account> findByEmail(String email);
// }

public interface AccountsRepository extends CrudRepository<Accounts, String>{

    //계정 관련
    @Query(value = "select count(*) from Accounts where uid=?1",nativeQuery=true)
    public int isExistUid(String uid);

    @Query(value = "select uid from Accounts where email=?1",nativeQuery = true)
    public String findIdByEmail(String email);

    @Query(value="select * from Accounts where uid=?1",nativeQuery = true)
    public Accounts findByUid(String uid);

}