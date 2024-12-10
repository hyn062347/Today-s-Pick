package com.dita.myapp.controller;

import com.dita.myapp.domain.Menu;
import com.dita.myapp.service.MenuService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/random")
    public Menu getRandomMenu(@RequestParam(required = false, defaultValue = "전체") String category) {
        return menuService.getRandomMenu(category);
    }
}
