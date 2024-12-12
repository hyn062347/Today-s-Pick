package com.dita.myapp.repository;

import com.dita.myapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Iterable<Recipe> findByMid(Long mid);
}
