package com.dita.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dita.myapp.domain.Favorite;
import com.dita.myapp.dto.FavoriteDto;
import com.dita.myapp.repository.FavoriteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public List<Favorite> getFavorite(FavoriteDto favoriteDto){
        List<Favorite> list;
        try {
            list=favoriteRepository.findByUidToFavorite(favoriteDto.getUid());
        } catch (Exception e) {
            throw e;
        }

        return list;
    }
}
