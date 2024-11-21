package com.dita.myapp.domain;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data 
//@Getter, @Setter, @ToString @EqualAndHashCode, @RequiredArgsConstructor = @Data
// https://dev-code-notepad.tistory.com/153 에 설명 되있음
@Entity
public class Account{

    @Id
    private String uid;
    private String password;
    private String name;
    private String email;
    private Timestamp timestamp;
}