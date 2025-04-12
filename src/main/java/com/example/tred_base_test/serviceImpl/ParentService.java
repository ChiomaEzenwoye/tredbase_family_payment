package com.example.tred_base_test.serviceImpl;

import com.example.tred_base_test.dto.StudentRequestDto;
import com.example.tred_base_test.exceptionHandler.UserException;
import com.example.tred_base_test.model.Parent;
import com.example.tred_base_test.model.Student;
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
    public Student addStudentToParent(Long parentId, StudentRequestDto studentDto) {
        // Retrieve the parent and associate them with the new student
        Parent parent = parentRepo.findById(parentId)
                .orElseThrow(() -> new UserException("Parent not found"));

        // Create the student and set its properties
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setBalance(studentDto.getBalance());

        // Add the student to the parent's set of students and vice versa
        student.getParents().add(parent);
        parent.getStudents().add(student);

        // Save the student and parent
        studentRepo.save(student);
        parentRepo.save(parent); // Save parent

        return student;
    }
}
