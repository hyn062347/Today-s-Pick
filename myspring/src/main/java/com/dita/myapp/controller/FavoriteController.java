package com.dita.myapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dita.myapp.domain.Favorite;
import com.dita.myapp.dto.AccountsDto;
import com.dita.myapp.dto.FavoriteDto;
import com.dita.myapp.service.FavoriteService;
import com.fasterxml.jackson.databind.util.JSONPObject;

import groovy.util.logging.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/favorite")
@Log
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/all")
    public ResponseEntity<?> getFavoriteAll(@RequestBody FavoriteDto favoriteDto) {
        ArrayList<FavoriteDto> list=new ArrayList<>();
        try{
            list=favoriteService.getFavorite(favoriteDto.getUid());
        }catch(Exception e){
            return ResponseEntity.ok().body(false);
        }
        
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteOne(@RequestBody FavoriteDto favoriteDto) {
        boolean b;
        try{
            b=favoriteService.deleteFavoriteOne(favoriteDto.getUid(),favoriteDto.getRid());
        }catch(Exception e){
            return ResponseEntity.ok().body(false);
        }
        
        return ResponseEntity.ok().body(b);
    }
    
}
