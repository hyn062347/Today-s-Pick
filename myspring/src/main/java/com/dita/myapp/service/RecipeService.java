package com.dita.myapp.service;

import com.dita.myapp.domain.Recipe;
import com.dita.myapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getRecipesByMenuId(Long mid) {
        return recipeRepository.findByMid(mid);
    }

    public Recipe getRecipeById(Long rid) {
        Optional<Recipe> optionalRecipe = recipeRepository.findRecipeById(rid);
        return optionalRecipe.orElse(null); // 값이 없으면 null 반환
    }
}
