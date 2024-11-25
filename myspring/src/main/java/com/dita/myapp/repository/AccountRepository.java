package com.dita.myapp.repository;

import com.dita.myapp.domain.Account;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

// import java.util.Optional;

// @Repository
// public interface AccountRepository extends JpaRepository<Account, String> {
//     Optional<Account> findByEmail(String email);
// }

public interface AccountRepository extends CrudRepository<Account, String>{

    //계정 관련
    @Query(value = "select count(*) from Account where uid=?1",nativeQuery=true)
    public int isExistUid(String uid);

    @Query(value = "select uid from Account where email=?1",nativeQuery = true)
    public String findIdByEmail(String email);

    @Query(value="select * from Account where uid=?1",nativeQuery = true)
    public List<Account> findByUid(String uid);

}