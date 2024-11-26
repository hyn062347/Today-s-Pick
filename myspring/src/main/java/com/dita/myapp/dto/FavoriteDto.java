package com.dita.myapp.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FavoriteDto {
    
    private String uid;
    private Long mid;
    private Timestamp added_at;
}
