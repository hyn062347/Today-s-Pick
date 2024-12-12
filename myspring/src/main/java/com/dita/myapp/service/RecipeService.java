package com.dita.myapp.service;

import com.dita.myapp.domain.Recipe;
import com.dita.myapp.repository.RecipeRepository;
import com.dita.myapp.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, MenuRepository menuRepository) {
        this.recipeRepository = recipeRepository;
        this.menuRepository = menuRepository;
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Long findMenuIdByName(String name) {
        try {
            Long mid = menuRepository.findMenuIdByName(name);
            System.out.println("findMenuIdByName() - name: " + name + ", mid: " + mid);
            return mid;
        } catch (Exception e) {
            System.err.println("Error in findMenuIdByName: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Iterable<Recipe> getRecipesByMenuId(Long mid) {
        try {
            System.out.println("Fetching recipes by mid: " + mid);
            return recipeRepository.findByMid(mid);
        } catch (Exception e) {
            System.err.println("Error in getRecipesByMenuId: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
