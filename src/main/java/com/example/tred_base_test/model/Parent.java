package com.example.tred_base_test.model;

import com.example.tred_base_test.model.Student;
import com.example.tred_base_test.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Parent extends User {

    @ManyToMany(mappedBy = "parents")
    private Set<Student> students = new HashSet<>();

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", balance=" + getBalance() +
                '}';
    }
}
