package com.dita.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dita.myapp.domain.Menu;

public interface MenuRepository extends CrudRepository<Menu, String>{
    
    //가챠 관련
    @Query(value = "select * from Menu  WHERE ctg=?1 order by RAND() LIMIT 1",nativeQuery = true)
    public List<Menu> gachaOneMenu(String ctg);
}
