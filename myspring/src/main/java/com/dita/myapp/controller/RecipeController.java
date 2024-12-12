package com.dita.myapp.controller;

import com.dita.myapp.domain.Recipe;
import com.dita.myapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

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
            @RequestParam("image") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("recipeTitle") String recipeTitle,
            @RequestParam("category") String category,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("instructions") String instructions,
            @RequestParam("uid") String uid) {
        try {
            System.out.println("Received name: " + name);
            System.out.println("Received uid: " + uid);

            Long mid = recipeService.findMenuIdByName(name);
            System.out.println("Found mid: " + mid);

            if (mid == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Invalid menu name", "message", "Menu not found: " + name));
            }

            String folder = "uploads/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(folder + fileName);

            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            Recipe recipe = Recipe.builder()
                    .uid(uid)
                    .mid(mid)
                    .rimg_src(folder)
                    .rimg_name(fileName)
                    .recipe_title(recipeTitle)
                    .ingredients(ingredients)
                    .instructions(instructions)
                    .build();

            Recipe savedRecipe = recipeService.saveRecipe(recipe);
            System.out.println("Saved recipe: " + savedRecipe);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "Failed to save recipe",
                            "message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Object> getRecipesByMenuId(@RequestParam("mid") Long mid) {
        try {
            System.out.println("Fetching recipes for mid: " + mid);
            Iterable<Recipe> recipes = recipeService.getRecipesByMenuId(mid);
            return ResponseEntity.ok(recipes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "Failed to fetch recipes",
                            "message", e.getMessage()));
        }
    }
}
