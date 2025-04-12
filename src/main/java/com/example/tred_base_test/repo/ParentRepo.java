package com.example.tred_base_test.repo;

import com.example.tred_base_test.model.Parent;
import com.example.tred_base_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {
    Optional<Parent> findByUserName(String username);

}

