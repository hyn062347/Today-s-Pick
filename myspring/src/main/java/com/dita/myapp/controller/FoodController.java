package com.dita.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodController {

    @GetMapping("/api/foods")
    public List<String> getFoods() {
        return List.of("Pizza", "Burger", "Sushi", "Pasta");
    }
}