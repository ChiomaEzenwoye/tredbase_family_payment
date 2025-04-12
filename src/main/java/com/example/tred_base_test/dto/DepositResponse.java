package com.example.tred_base_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DepositResponse {
    private String message;
    private BigDecimal updatedBalance;
}
