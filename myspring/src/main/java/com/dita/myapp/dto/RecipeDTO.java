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
    private String name; // 메뉴 이름 (프론트의 "메뉴"와 매핑)
    private String fileSrc; // 파일 경로
    private String fileName; // 파일 이름
    private String recipeTitle; // 레시피 제목
    private String ingredients; // 재료 리스트
    private String instructions; // 요리 방법
}
