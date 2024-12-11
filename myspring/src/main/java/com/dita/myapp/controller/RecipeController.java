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
            @RequestParam("name") String name, // 메뉴 이름
            @RequestParam("recipeTitle") String recipeTitle,
            @RequestParam("category") String category,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("instructions") String instructions,
            @RequestParam("uid") String uid) {
        try {
            System.out.println("Received name: " + name); // 로그 추가
            System.out.println("Received uid: " + uid); // 로그 추가

            // 메뉴 이름으로 mid 조회
            Long mid = recipeService.findMenuIdByName(name);
            System.out.println("Found mid: " + mid); // 로그 추가

            if (mid == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Invalid menu name", "message", "Menu not found: " + name));
            }

            // 이미지 저장 경로 설정
            String folder = "uploads/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(folder + fileName);

            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            // Recipe 생성
            Recipe recipe = Recipe.builder()
                    .uid(uid)
                    .mid(mid) // 조회한 메뉴 ID 설정
                    .rimg_src(folder)
                    .rimg_name(fileName)
                    .recipe_title(recipeTitle)
                    .ingredients(ingredients)
                    .instructions(instructions)
                    .build();

            Recipe savedRecipe = recipeService.saveRecipe(recipe);
            System.out.println("Saved recipe: " + savedRecipe); // 로그 추가

            return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "Failed to save recipe",
                            "message", e.getMessage()));
        }
    }
}
