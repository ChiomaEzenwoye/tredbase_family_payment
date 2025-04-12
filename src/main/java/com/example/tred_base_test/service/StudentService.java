package com.example.tred_base_test.service;

import com.example.tred_base_test.dto.StudentRequestDto;
import com.example.tred_base_test.exceptionHandler.UserException;
import com.example.tred_base_test.jwt.JwtTokenUtil;
import com.example.tred_base_test.model.Parent;
import com.example.tred_base_test.model.Student;
import com.example.tred_base_test.repo.ParentRepo;
import com.example.tred_base_test.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final JwtTokenUtil jwtTokenUtil;
    private final ParentRepo parentRepo;
    private final StudentRepo studentRepo;

    public Student registerStudent(StudentRequestDto studentDto, String token) {
        String parentUsername = jwtTokenUtil.extractUsername(token);
        Parent parent = parentRepo.findByUserName(parentUsername)
                .orElseThrow(() -> new UserException("Parent not found"));

        Student student = new Student();
        student.setName(studentDto.getName());
        student.setBalance(studentDto.getBalance());

        if (student.getParents() == null) {
            student.setParents(new HashSet<>());
        }
        student.getParents().add(parent);

        // Save only the owning side
        return studentRepo.save(student);
    }


}
