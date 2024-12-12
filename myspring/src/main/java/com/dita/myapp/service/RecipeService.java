package com.dita.myapp.service;

import com.dita.myapp.domain.Recipe;
import com.dita.myapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return recipeRepository.findById(rid);
    }
}
