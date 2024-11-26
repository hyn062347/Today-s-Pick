package com.dita.myapp.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid; // Primary Key

    @Column(nullable = false, length = 15)
    private String uid; // 작성자 UID

    @Column(nullable = false)
    private String name; // 메뉴 이름 (프론트의 "메뉴"와 매핑)

    @Column(nullable = false)
    private String fileSrc; // 파일 경로

    @Column(nullable = false)
    private String fileName; // 파일 이름

    @Column(nullable = false)
    private String recipeTitle; // 레시피 제목

    @Column(nullable = false)
    private String ingredients; // 재료 리스트

    @Column(nullable = false, length = 1000)
    private String instructions; // 요리 방법

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String editedAt; // 수정 시간
}
