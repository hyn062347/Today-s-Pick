package com.dita.myapp.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data

public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mid; // Primary Key

    @Column(name = "mname")
    private String mname; // 메뉴 이름

    @Column(name = "ctg")
    private String ctg; // 카테고리 (외래 키)
}
