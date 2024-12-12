package com.dita.myapp.domain;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity
@IdClass(FavoriteId.class)
public class Favorite{
    
    @Id
    private String uid;

    @Id
    private Long rid;

    private Timestamp added_at;


}
