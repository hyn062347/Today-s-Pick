package com.dita.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dita.myapp.domain.Favorite;
import com.dita.myapp.domain.FavoriteId;

public interface FavoriteRepository extends CrudRepository<Favorite, FavoriteId>{

    //즐찾 관련
    @Query(value = "select count(*) from Favorite where uid=?1 and rid=?2",nativeQuery = true)
    public int findByFavorite(String uid,Long rid);

    @Query(value = "SELECT favorite.rid, ifnull(r.rimg_src,'/img/'), ifnull(r.rimg_name,'noImg.png'), r.recipe_title, r.mname, r.ctg, favorite.added_at FROM favorite JOIN (SELECT recipe.rid, recipe.rimg_src, recipe.rimg_name, recipe.recipe_title, menu.mname, menu.ctg FROM recipe JOIN menu ON recipe.mid=menu.mid) r ON favorite.rid=r.rid WHERE uid=?1",nativeQuery = true)
    public List<Object[]> findByUidToFavorite(String uid);
    
}
