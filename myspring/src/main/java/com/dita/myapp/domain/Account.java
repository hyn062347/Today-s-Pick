package com.dita.myapp.domain;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
//@Getter, @Setter, @ToString @EqualAndHashCode, @RequiredArgsConstructor = @Data
// https://dev-code-notepad.tistory.com/153 에 설명 되있음
@Entity
public class Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String password;
    private String name;
    private String email;
    private Timestamp timestamp;
}