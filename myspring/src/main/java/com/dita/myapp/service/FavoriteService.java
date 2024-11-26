package com.dita.myapp.service;

import org.springframework.stereotype.Service;

import com.dita.myapp.domain.Favorite;
import com.dita.myapp.dto.FavoriteDto;
import com.dita.myapp.repository.FavoriteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public int getFavorite(FavoriteDto favoriteDto){
        Favorite favorite = new Favorite();
        favorite.getMid()
    }
}
