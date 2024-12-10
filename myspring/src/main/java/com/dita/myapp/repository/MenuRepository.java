package com.dita.myapp.repository;

import com.dita.myapp.domain.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer> {

    @Query(value = "SELECT * FROM menu WHERE (:ctg IS NULL OR ctg = :ctg) ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Menu findRandomMenuByCategory(@Param("ctg") String category);
}