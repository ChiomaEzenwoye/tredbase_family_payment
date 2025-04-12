package com.example.tred_base_test.service;

import com.example.tred_base_test.dto.RegisterRequestDto;
import com.example.tred_base_test.dto.UserResponseDto;
import com.example.tred_base_test.exceptionHandler.UserException;
import com.example.tred_base_test.jwt.JwtTokenUtil;
import com.example.tred_base_test.model.Parent;
import com.example.tred_base_test.model.Student;
import com.example.tred_base_test.model.User;
import com.example.tred_base_test.repo.ParentRepo;
import com.example.tred_base_test.repo.StudentRepo;
import com.example.tred_base_test.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final ParentRepo parentRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final StudentRepo studentRepo;

    public AuthService(UserRepo userRepo, ParentRepo parentRepo, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, StudentRepo studentRepo) {
        this.userRepo = userRepo;
        this.parentRepo = parentRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.studentRepo = studentRepo;
    }

    public UserResponseDto register(RegisterRequestDto dto) {
        Optional<User> existingUser = userRepo.findByUserName(dto.getUsername());

        if (existingUser.isPresent()) {
            throw new UserException("Username already taken");
        }

        // Create and save the parent (who is also a User)
        Parent parent = new Parent();
        parent.setUserName(dto.getUsername());
        parent.setPassword(passwordEncoder.encode(dto.getPassword()));
        parent.setBalance(BigDecimal.ZERO);  // Default balance

        // Create and associate the student
        Student student = new Student();
        student.setName(dto.getStudent().getName());
        student.setBalance(dto.getStudent().getBalance());

        // Bidirectional linking
        student.getParents().add(parent);
        parent.getStudents().add(student);

        // Save both sides
        parentRepo.save(parent);
        studentRepo.save(student);

        // Prepare response
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(parent.getId());
        responseDto.setUsername(parent.getUsername());
        responseDto.setStudentId(student.getId());

        return responseDto;
    }



    public String login(String username, String password) {
        User user = userRepo.findByUserName(username)
                .orElseThrow(() -> new UserException("Username " + username + " not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtTokenUtil.generateToken(username);
    }
}
