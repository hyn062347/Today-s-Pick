package com.dita.myapp.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "ctg")
    private String ctg; // 카테고리 이름 (Primary Key)
}