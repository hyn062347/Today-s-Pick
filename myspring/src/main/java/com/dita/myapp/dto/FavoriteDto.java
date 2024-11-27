package com.dita.myapp.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FavoriteDto {
    
    private String uid;
    private Long rid;
    private Timestamp added_at;
    private String mname;
    private String ctg;
    private String rimg_src;
    private String rimg_name;
}
