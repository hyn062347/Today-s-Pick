package com.dita.myapp.service;

import com.dita.myapp.domain.Menu;
import com.dita.myapp.domain.Recipe;
import com.dita.myapp.repository.MenuRepository;
import com.dita.myapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public List<Recipe> getRecipesByMenuId(Long mid) {
        return recipeRepository.findByMid(mid);
    }

    public Recipe getRecipeById(Long rid) {
        Optional<Recipe> optionalRecipe = recipeRepository.findRecipeById(rid);
        return optionalRecipe.orElse(null);
    }

    public Recipe findRecipeById(Long rid) {
        Optional<Recipe> recipe = recipeRepository.findById(rid);
        return recipe.orElseThrow(() -> new IllegalArgumentException("Recipe not found for ID: " + rid));
    }

    public Map<String, Object> getMenuDetails(Long mid) {
        // MenuRepository에서 데이터 가져오기, 없으면 기본값 반환
        return menuRepository.findMenuDetails(mid)
                .orElse(Map.of("menu_name", "Unknown", "category", "Unknown")); // 기본값 반환
    }

    public Map<String, Object> getRecipeDetails(Long rid) {
        // 레시피 가져오기
        Recipe recipe = findRecipeById(rid);
        if (recipe == null) {
            throw new IllegalArgumentException("Recipe not found for ID: " + rid);
        }

        // 메뉴 세부 정보 가져오기
        Map<String, Object> menuDetails = getMenuDetails(recipe.getMid());

        // 이미지 경로가 null일 경우 기본값 설정
        String rimgSrc = (recipe.getRimg_src() != null) ? recipe.getRimg_src() : "/default/path/";
        String rimgName = (recipe.getRimg_name() != null) ? recipe.getRimg_name() : "default_image.jpg";

        // 레시피와 메뉴 세부 정보를 합쳐서 반환
        return Map.of(
                "rid", recipe.getRid(),
                "uid", recipe.getUid(),
                "mid", recipe.getMid(),
                "rimg_src", rimgSrc,
                "rimg_name", rimgName,
                "recipe_title", recipe.getRecipe_title(),
                "ingredients", recipe.getIngredients(),
                "instructions", recipe.getInstructions(),
                "menu_name", menuDetails.get("menu_name"),
                "category", menuDetails.get("category"));
    }

    public Iterable<Recipe> getRecipesByUid(String uid) {
        return recipeRepository.findByUid(uid);
    }

}
