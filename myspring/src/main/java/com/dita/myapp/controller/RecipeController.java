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
    public ResponseEntity<?> getRecipeDetails(@RequestBody Map<String, Long> request) {
        Long rid = request.get("rid");
        if (rid == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing rid parameter"));
        }

        try {
            Map<String, Object> recipeDetails = recipeService.getRecipeDetails(rid);
            return ResponseEntity.ok(recipeDetails);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred", "message", e.getMessage()));
        }
    }

    @PostMapping("/by-uid")
    public ResponseEntity<Object> getRecipesByUid(@RequestBody Map<String, String> payload) {
        try {
            String uid = payload.get("uid");
            if (uid == null || uid.isEmpty()) {
                throw new IllegalArgumentException("UID is required");
            }

            Iterable<Recipe> recipes = recipeService.getRecipesByUid(uid);
            return ResponseEntity.ok(recipes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Failed to fetch recipes by UID", "message", e.getMessage()));
        }
    }

}
