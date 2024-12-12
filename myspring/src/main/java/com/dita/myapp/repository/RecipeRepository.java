package com.dita.myapp.repository;

import com.dita.myapp.domain.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r WHERE r.mid = :mid")
    List<Recipe> findByMid(@Param("mid") Long mid);

    @Query("SELECT r FROM Recipe r WHERE r.rid = :rid")
    Recipe findById(@Param("rid") Long rid);
}
