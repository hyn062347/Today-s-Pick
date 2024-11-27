package com.dita.myapp.controller;

import com.dita.myapp.domain.Recipe;
import com.dita.myapp.dto.RecipeDTO;
import com.dita.myapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * 레시피 등록 API
     *
     * @param file         업로드할 이미지 파일
     * @param recipeTitle  레시피 제목
     * @param category     분류
     * @param ingredients  재료 리스트
     * @param instructions 요리 방법
     * @param uid          작성자 UID
     * @return 성공 또는 실패 메시지
     */
    @PostMapping
    public ResponseEntity<String> addRecipe(
            @RequestParam("image") MultipartFile file,
            @RequestParam("name") String name, // 메뉴 이름
            @RequestParam("recipeTitle") String recipeTitle,
            @RequestParam("category") String category,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("instructions") String instructions,
            @RequestParam("uid") String uid) {
        try {
            // 이미지 저장 경로 설정
            String folder = "uploads/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(folder + fileName);

            // 폴더 생성 (존재하지 않으면)
            Files.createDirectories(filePath.getParent());

            // 파일 저장
            Files.write(filePath, file.getBytes());

            // DTO 생성
            RecipeDTO recipeDTO = RecipeDTO.builder()
                    .uid(uid)
                    .rimg_src(folder)
                    .rimg_name(fileName)
                    .recipe_title(recipeTitle)
                    .ingredients(ingredients)
                    .instructions(instructions)
                    .build();

            // DTO -> Entity 변환 및 데이터 저장
            Recipe recipe = Recipe.builder()
                    .uid(recipeDTO.getUid())
                    .rimg_src(recipeDTO.getRimg_src())
                    .rimg_name(recipeDTO.getRimg_name())
                    .recipe_title(recipeDTO.getRecipe_title())
                    .ingredients(recipeDTO.getIngredients())
                    .instructions(recipeDTO.getInstructions())
                    .build();

            recipeService.saveRecipe(recipe);

            return ResponseEntity.status(HttpStatus.CREATED).body("Recipe added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save recipe: " + e.getMessage());
        }
    }

}
