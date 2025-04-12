package com.example.tred_base_test.controller;

import com.example.tred_base_test.dto.PaymentRequestDto;
import com.example.tred_base_test.jwt.JwtTokenUtil;
import com.example.tred_base_test.model.Payment;
import com.example.tred_base_test.repo.PaymentRepo;
import com.example.tred_base_test.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private JwtTokenUtil jwtService;

    @PostMapping("/make-payment")
    public ResponseEntity<String> makePayment(@RequestBody PaymentRequestDto request,
                                              @RequestHeader("Authorization") String authHeader) {
        // Extract the token and username from the Authorization header
        String token = authHeader.substring(7);  // Extracting the token part from "Bearer <token>"
        String username = jwtService.extractUsername(token);

        // Optionally log the username for debugging/auditing purposes
        System.out.println("Payment made by user: " + username);

        // Pass the username to the payment service so that the correct user's balance is updated
        paymentService.processPayment(request, username);

        return ResponseEntity.ok("Payment processed successfully.");
    }


}

