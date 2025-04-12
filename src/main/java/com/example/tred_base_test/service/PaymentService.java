package com.example.tred_base_test.service;

import com.example.tred_base_test.model.Payment;

public interface PaymentService {
    Payment createPayment(Payment payment, String username);
    Payment getPayment(Long id);

}
