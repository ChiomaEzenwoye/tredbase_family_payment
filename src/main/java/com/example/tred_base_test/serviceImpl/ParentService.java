package com.example.tred_base_test.serviceImpl;

import com.example.tred_base_test.dto.StudentRequestDto;
import com.example.tred_base_test.exceptionHandler.UserException;
import com.example.tred_base_test.model.Parent;
import com.example.tred_base_test.model.Student;
import com.example.tred_base_test.model.User;
import com.example.tred_base_test.repo.ParentRepo;
import com.example.tred_base_test.repo.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParentService {

    private final ParentRepo parentRepo;
    private final StudentRepo studentRepo;

    public ParentService(ParentRepo parentRepo, StudentRepo studentRepo) {
        this.parentRepo = parentRepo;
        this.studentRepo = studentRepo;
    }

    @Transactional
    public Student addStudentToParent(User user, StudentRequestDto studentDto) {
        Parent parent = parentRepo.findByUserName(user.getUsername())
                .orElseThrow(() -> new UserException("Parent not found"));

        Student student = new Student();
        student.setName(studentDto.getName());
        student.setBalance(studentDto.getBalance());

        student.getParents().add(parent);
        parent.getStudents().add(student);

        studentRepo.save(student);
        parentRepo.save(parent);

        return student;
    }

}
