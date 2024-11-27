package com.dita.myapp.domain;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    private Long rid; // Primary Key

    private String uid; // 작성자 UID

    private Long mid;

    private String rimg_src; // 파일 경로

    private String rimg_name; // 파일 이름

    private String recipe_title; // 레시피 제목

    private String ingredients; // 재료 리스트

    private String instructions; // 요리 방법

    @CurrentTimestamp
    private String edited_at; // 수정 시간
}