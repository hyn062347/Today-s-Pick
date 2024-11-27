package com.dita.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private String uid; // 작성자 UID
    private String rimg_src; // 파일 경로
    private String rimg_name; // 파일 이름
    private String recipe_title; // 레시피 제목
    private String ingredients; // 재료 리스트
    private String instructions; // 요리 방법
}
