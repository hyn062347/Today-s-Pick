package com.dita.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dita.myapp.domain.foodtest;

import java.util.List;

@RestController
@ResponseBody
public class FoodController {
    
    @GetMapping("/api/foods")
    public foodtest getFoods() {
        foodtest ft=new foodtest("Pizza", "Burger", "Sushi", "Pasta");
        return ft;
    }
}