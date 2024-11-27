package com.dita.myapp.domain;

import java.io.Serializable;

import groovy.transform.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PreferenceId implements Serializable {
    String uid;
    String ctg;
}
