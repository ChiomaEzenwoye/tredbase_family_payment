package com.example.tred_base_test.controller;

import com.example.tred_base_test.model.User;
import com.example.tred_base_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Update user balance
    @PostMapping("/deposit")
    public ResponseEntity<?> updateBalance(@AuthenticationPrincipal User user, @RequestBody BigDecimal amount) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }
        user.setBalance(user.getBalance().add(amount));  // Add the amount to the current balance
        userService.deposit(user, amount);  // Save the updated user

        return ResponseEntity.ok(user.getBalance());  // Return the updated balance
    }
}
