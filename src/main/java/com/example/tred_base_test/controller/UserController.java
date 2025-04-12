package com.example.tred_base_test.controller;

import com.example.tred_base_test.dto.DepositResponse;
import com.example.tred_base_test.model.User;
import com.example.tred_base_test.serviceimpl.UserService;
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


    @PostMapping("/deposit")
    public ResponseEntity<?> updateBalance(@AuthenticationPrincipal User user, @RequestBody BigDecimal amount) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        user.setBalance(user.getBalance().add(amount));
        userService.deposit(user, amount);

        DepositResponse response = new DepositResponse("Deposit successful", user.getBalance());
        return ResponseEntity.ok(response);
    }

}
