package com.dita.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dita.myapp.domain.FavoriteId;
import com.dita.myapp.dto.FavoriteDto;
import com.dita.myapp.repository.FavoriteRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Service
@Log
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public ArrayList<FavoriteDto> getFavorite(String uid){
        List<Object[]> list;
        try {
            list=favoriteRepository.findByUidToFavorite(uid);
        } catch (Exception e) {
            throw e;
        }
        
        ArrayList<FavoriteDto> fdto=new ArrayList<FavoriteDto>();
        for (Object[] objects : list) {
            FavoriteDto f=new FavoriteDto();
            f.setUid(uid);
            f.setRid(Long.parseLong(objects[0].toString()));
            f.setRimg_src(objects[1].toString());
            f.setRimg_name(objects[2].toString());
            f.setRecipe_title(objects[3].toString());
            f.setMname(objects[4].toString());
            f.setCtg(objects[5].toString());
            f.setAdded_at(java.sql.Timestamp.valueOf(objects[6].toString()));

            fdto.add(f);
        }

        return fdto;
    }

    public boolean deleteFavoriteOne(String uid,Long rid){
        int i;
        boolean result=false;
        System.out.println(uid+"     log      "+rid);
        try {
            i=favoriteRepository.findByFavorite(uid, rid);
            if(i>=1){
                favoriteRepository.deleteById(new FavoriteId(uid,rid));
                result=true;
            }
            
        } catch (Exception e) {
            throw e;
        }
        
        return result;
    }

}
