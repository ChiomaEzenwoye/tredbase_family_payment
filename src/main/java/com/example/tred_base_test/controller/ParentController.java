package com.example.tred_base_test.controller;

import com.example.tred_base_test.dto.StudentRequestDto;
import com.example.tred_base_test.dto.StudentResponseDto;
import com.example.tred_base_test.model.Student;
import com.example.tred_base_test.model.User;
import com.example.tred_base_test.serviceImpl.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parents")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }



    @PostMapping("/students")
    public ResponseEntity<StudentResponseDto> addStudentToParent(@AuthenticationPrincipal User user,
                                                                 @RequestBody StudentRequestDto studentDto) {
        StudentResponseDto student = parentService.addStudentToParent(user.getId(), studentDto);
        return ResponseEntity.ok(student);
    }


}
