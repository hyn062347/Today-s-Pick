package com.dita.myapp.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
