package com.dita.myapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dita.myapp.domain.Favorite;
import com.dita.myapp.dto.AccountsDto;
import com.dita.myapp.dto.FavoriteDto;
import com.dita.myapp.service.FavoriteService;

import groovy.util.logging.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/favorite")
@Log
public class FavoriteController {
    FavoriteService favoriteService;

    @GetMapping("/all")
    public ResponseEntity<?> getFavoriteAll(@RequestBody FavoriteDto favoriteDto) {
        List<Favorite> list;
        try{
            list=favoriteService.getFavorite(favoriteDto);
        }catch(Exception e){
            return ResponseEntity.ok().body(false);
        }
        
        return ResponseEntity.ok().body(list);
    }
    
}
