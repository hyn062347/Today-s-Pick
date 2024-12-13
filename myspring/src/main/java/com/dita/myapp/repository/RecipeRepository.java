package com.dita.myapp.repository;

import com.dita.myapp.domain.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r WHERE r.mid = :mid")
    List<Recipe> findByMid(@Param("mid") Long mid);

    // 이름 변경으로 충돌 해결
    @Query("SELECT r FROM Recipe r WHERE r.rid = :rid")
    Optional<Recipe> findRecipeById(@Param("rid") Long rid);
}
