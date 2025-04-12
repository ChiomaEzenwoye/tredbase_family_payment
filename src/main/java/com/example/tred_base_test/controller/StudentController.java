package com.example.tred_base_test.controller;

import com.example.tred_base_test.dto.StudentRequestDto;
import com.example.tred_base_test.model.Student;
import com.example.tred_base_test.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> registerStudent(
            //this add more student to the parent
            @RequestBody StudentRequestDto studentDto,
            @RequestHeader("Authorization") String token
    ) {
        Student student = studentService.registerStudent(studentDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
}
