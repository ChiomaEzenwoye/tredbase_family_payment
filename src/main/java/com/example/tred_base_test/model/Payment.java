package com.example.tred_base_test.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Payment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long parentId;

    private Long studentId;

    private BigDecimal amount;

    private LocalDateTime timestamp;
    @Column
    private String createdBy;
}