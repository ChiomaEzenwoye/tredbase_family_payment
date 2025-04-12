package com.example.tred_base_test.repo;

import com.example.tred_base_test.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {
}

