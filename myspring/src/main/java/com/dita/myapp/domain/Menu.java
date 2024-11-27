package com.dita.myapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Menu {

    @Id
    private Long mid;
    
    private String mname;
    private String ctg;

    
}
