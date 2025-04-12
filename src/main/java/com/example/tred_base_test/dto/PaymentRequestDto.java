package com.example.tred_base_test.dto;

import lombok.Data;

@Data
public class PaymentRequestDto {

    private Long parentId;
    private Long studentId;
    private Double paymentAmount;


}


