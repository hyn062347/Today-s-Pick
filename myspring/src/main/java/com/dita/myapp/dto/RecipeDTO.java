package com.dita.myapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDTO {
    private Long rid; // 레시피 ID
    private String uid; // 작성자 UID
    private Long mid; // 메뉴 ID
    private String menu_name; // 메뉴 이름
    private String category; // 메뉴 카테고리
    private String rimg_src; // 이미지 경로
    private String rimg_name; // 이미지 이름
    private String recipe_title; // 레시피 제목
    private String ingredients; // 재료
    private String instructions; // 요리 방법
}
