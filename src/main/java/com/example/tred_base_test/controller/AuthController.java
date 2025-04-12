package com.example.tred_base_test.controller;

import com.example.tred_base_test.dto.RegisterRequestDto;
import com.example.tred_base_test.model.User;
import com.example.tred_base_test.service.AuthService;
import com.example.tred_base_test.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequestDto registerRequestDto) {
        User registeredUser = userService.registerUser(registerRequestDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequestDto loginRequest) {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }
}
