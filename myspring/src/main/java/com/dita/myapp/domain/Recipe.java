package com.dita.myapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid; // Primary Key

    private String uid; // 작성자 UID

    private Long mid; // 메뉴 ID

    private String rimg_src; // 이미지 파일 경로

    private String rimg_name; // 이미지 파일 이름

    private String recipe_title; // 레시피 제목

    private String ingredients; // 재료 리스트

    private String instructions; // 요리 방법

    @Column(insertable = false, updatable = false)
    private Timestamp edited_at; // 수정 시간
}
