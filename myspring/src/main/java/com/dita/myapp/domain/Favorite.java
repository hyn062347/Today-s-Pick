package com.dita.myapp.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CurrentTimestamp;

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
    private Long mid;

    @CurrentTimestamp
    private Timestamp added_at;
}
