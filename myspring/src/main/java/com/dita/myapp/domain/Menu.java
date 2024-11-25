package com.dita.myapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Menu {
    @Id
    private int mid;
    
    private String mname;
    private String ctg;
}
