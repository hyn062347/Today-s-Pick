package com.dita.myapp.repository;

import com.dita.myapp.domain.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer> {

    @Query("SELECT m FROM Menu m WHERE m.ctg IN :categories ORDER BY RAND() LIMIT 1")
    Menu findRandomMenuByCategories(@Param("categories") List<String> categories);

}
