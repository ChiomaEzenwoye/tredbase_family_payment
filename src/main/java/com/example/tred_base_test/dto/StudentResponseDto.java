package com.example.tred_base_test.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StudentResponseDto {
    private Long id;
    private String name;
    private BigDecimal balance;


}
