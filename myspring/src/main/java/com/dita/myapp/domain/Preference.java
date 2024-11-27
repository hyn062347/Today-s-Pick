package com.dita.myapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity
@IdClass(PreferenceId.class)
public class Preference {
    @Id
    String uid;

    @Id
    String ctg;
}
