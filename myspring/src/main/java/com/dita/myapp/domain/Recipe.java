package com.dita.myapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid; // 레시피 ID

    private String uid; // 작성자 UID

    private Long mid; // 메뉴 ID

    private String rimg_src; // 이미지 경로

    private String rimg_name; // 이미지 이름

    private String recipe_title; // 레시피 제목

    private String ingredients; // 재료

    private String instructions; // 요리 방법

    private Timestamp edited_at; // 수정 시간
}
