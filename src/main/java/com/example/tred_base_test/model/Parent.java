package com.example.tred_base_test.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private BigDecimal balance;

    @ManyToMany(mappedBy = "parents")
    private Set<Student> students;
}
