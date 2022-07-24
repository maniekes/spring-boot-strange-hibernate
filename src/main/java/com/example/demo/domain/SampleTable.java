package com.example.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SampleTable {
    @Id Long id1;
    Long id2;
    String message;
}
