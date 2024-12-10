package com.dita.myapp.controller;

import com.dita.myapp.domain.Menu;
import com.dita.myapp.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/api/menus/random")
    public ResponseEntity<Menu> getRandomMenu(
            @RequestParam(required = false) List<String> categories) {
        try {
            Menu randomMenu = menuService.findRandomMenu(categories);
            return ResponseEntity.ok(randomMenu);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
