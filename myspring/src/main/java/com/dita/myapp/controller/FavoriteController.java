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
        List<Object[]> list;
        try{
            list=favoriteService.getFavorite(favoriteDto.getUid());
        }catch(Exception e){
            return ResponseEntity.ok().body(false);
        }

        ArrayList<FavoriteDto> fdto=new ArrayList<FavoriteDto>();
        for (Object[] objects : list) {
            FavoriteDto f=new FavoriteDto();
            f.setUid(objects[0].toString());
            f.setRid(Long.parseLong(objects[1].toString()));
            f.setAdded_at(java.sql.Timestamp.valueOf(objects[2].toString()));
            f.setMname(objects[3].toString());
            f.setCtg(objects[4].toString());

            fdto.add(f);
        }
        
        return ResponseEntity.ok().body(fdto);
    }
    
}
