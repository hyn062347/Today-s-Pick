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

    // 이름으로 메뉴 ID 조회 메서드 추가
    @Query("SELECT m.mid FROM Menu m WHERE m.mname = :name")
    Long findMenuIdByName(@Param("name") String name);
}
