package com.dita.myapp.controller;

import java.util.ArrayList;
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
        List<Favorite> list;
        try{
            list=favoriteService.getFavorite(favoriteDto.getUid());
        }catch(Exception e){
            return ResponseEntity.ok().body(false);
        }
        System.out.println("this is log!!!!!!!!!!!!!!!!!!!!!!!!"+list);

        ArrayList<FavoriteDto> fdto=new ArrayList<FavoriteDto>();
        for (Favorite f : list) {
            FavoriteDto ff=new FavoriteDto();
            ff.setUid(f.getUid());
            ff.setMid(f.getMid());
            ff.setAdded_at(f.getAdded_at());

            fdto.add(ff);
        }

        System.out.println(fdto);
        return ResponseEntity.ok().body(fdto);
    }
    
}
