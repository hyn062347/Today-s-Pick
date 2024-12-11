package com.dita.myapp.repository;

import com.dita.myapp.domain.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer> {

    @Query(value = "SELECT * FROM menu WHERE ctg IN :categories ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Menu findRandomMenuByCategories(@Param("categories") List<String> categories);

    @Query("SELECT m.mid FROM Menu m WHERE m.mname = :name")
    Long findMenuIdByName(@Param("name") String name); // 이름으로 메뉴 ID 조회
}
