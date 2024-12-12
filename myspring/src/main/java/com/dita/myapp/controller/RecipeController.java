package com.dita.myapp.controller;

import com.dita.myapp.domain.Recipe;
import com.dita.myapp.dto.RecipeDTO;
import com.dita.myapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Object> addRecipe(
            @RequestParam("image") String image,
            @RequestParam("name") String name,
            @RequestParam("recipeTitle") String recipeTitle,
            @RequestParam("category") String category,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("instructions") String instructions,
            @RequestParam("uid") String uid) {
        try {
            Recipe recipe = Recipe.builder()
                    .uid(uid)
                    .mid(1L) // For simplicity, replace this with real menu ID logic.
                    .rimg_src("uploads/")
                    .rimg_name(image)
                    .recipe_title(recipeTitle)
                    .ingredients(ingredients)
                    .instructions(instructions)
                    .build();

            Recipe savedRecipe = recipeService.saveRecipe(recipe);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Failed to save recipe", "message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Object> getRecipesByMenuId(@RequestParam("mid") Long mid) {
        try {
            List<Recipe> recipes = recipeService.getRecipesByMenuId(mid);
            if (recipes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "No recipes found", "message", "No recipes found for menu ID: " + mid));
            }
            return ResponseEntity.ok(recipes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to fetch recipes", "message", e.getMessage()));
        }
    }

    @PostMapping("/details")
    public ResponseEntity<Object> getRecipeDetails(@RequestBody Map<String, Long> payload) {
        try {
            Long rid = payload.get("rid");
            if (rid == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Invalid request", "message", "Missing recipe ID"));
            }

            Recipe recipe = recipeService.getRecipeById(rid);
            if (recipe == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Recipe not found", "message", "No recipe found for ID: " + rid));
            }

            RecipeDTO recipeDTO = RecipeDTO.builder()
                    .rid(recipe.getRid())
                    .uid(recipe.getUid())
                    .mid(recipe.getMid())
                    .rimg_src(recipe.getRimg_src())
                    .rimg_name(recipe.getRimg_name())
                    .recipe_title(recipe.getRecipe_title())
                    .ingredients(recipe.getIngredients())
                    .instructions(recipe.getInstructions())
                    .menu_name("Example Menu Name") // Replace with menu name lookup logic
                    .category("Example Category")  // Replace with category lookup logic
                    .build();

            return ResponseEntity.ok(recipeDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to fetch recipe details", "message", e.getMessage()));
        }
    }
}
