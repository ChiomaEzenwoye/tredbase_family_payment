package com.example.tred_base_test.dto;

import lombok.Data;




@Data
public class RegisterRequestDto {

    private String username;    // Parent's username
    private String password;    // Parent's password
    private StudentRequestDto student;  // Nested DTO for student data

}
