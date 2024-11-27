package com.dita.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dita.myapp.domain.Favorite;
import com.dita.myapp.domain.FavoriteId;

public interface FavoriteRepository extends CrudRepository<Favorite, FavoriteId>{

    //즐찾 관련
    @Query(value = "select count(*) from Favorite where uid=?1 and mid=?2",nativeQuery = true)
    public void findByFavorite(String uid,int mid);

    @Query(value = "SELECT favorite.uid, favorite.mid, favorite.added_at, menu.mname, menu.ctg FROM favorite JOIN menu ON favorite.mid = menu.mid WHERE favorite.uid = ?1",nativeQuery = true)
    public List<Object[]> findByUidToFavorite(String uid);
    

}
