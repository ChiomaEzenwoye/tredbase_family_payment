package com.example.tred_base_test.controller;

import com.example.tred_base_test.dto.AuthRequestDto;
import com.example.tred_base_test.dto.RegisterRequestDto;
import com.example.tred_base_test.dto.UserResponseDto;
import com.example.tred_base_test.serviceimpl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class AuthController {
    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody RegisterRequestDto dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto dto) {
        String token = authService.login(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}

