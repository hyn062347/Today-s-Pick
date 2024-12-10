package com.dita.myapp.service;

import com.dita.myapp.domain.Menu;
import com.dita.myapp.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu findRandomMenu(List<String> categories) {
        if (categories == null || categories.isEmpty()) {
            throw new IllegalArgumentException("카테고리 목록이 비어 있습니다.");
        }

        return menuRepository.findRandomMenuByCategories(categories);
    }

    private List<String> getSubCategoriesByMainCategory(String mainCategory) {
        Map<String, List<String>> categoryMap = new HashMap<>();
        categoryMap.put("음료", Arrays.asList("음료 및 차류", "유제품류 및 빙과류"));
        categoryMap.put("간식", Arrays.asList("과일류", "두류, 견과 및 종실류", "빵 및 과자류", "유제품류 및 빙과류", "곡류, 서류 제품"));
        categoryMap.put("식사", Arrays.asList("밥류", "볶음류", "면 및 만두류", "육류", "수·조·어·육류", "구이류", "튀김류", "국 및 탕류", "찜류",
                "찌개 및 전골류", "조림류"));
        categoryMap.put("반찬",
                Arrays.asList("젓갈류", "장아찌·절임류", "김치류", "생채·무침류", "죽 및 스프류", "전·적 및 부침류", "장류, 양념류", "나물·숙채류"));

        return categoryMap.getOrDefault(mainCategory, Collections.emptyList());
    }

}
