package com.example.tred_base_test.controller;

import com.example.tred_base_test.dto.StudentRequestDto;
import com.example.tred_base_test.model.Student;
import com.example.tred_base_test.serviceImpl.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parents")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping("/{parentId}/students")
    public ResponseEntity<Student> addStudent(@PathVariable Long parentId, @RequestBody StudentRequestDto studentDto) {
        // Call the service method to add the student to the parent
        Student student = parentService.addStudentToParent(parentId, studentDto);
        
        // Return the student in the response
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }


}
