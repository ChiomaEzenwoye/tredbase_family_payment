package com.example.tred_base_test.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String password;
    private String name;  // The name of the parent or user
}
