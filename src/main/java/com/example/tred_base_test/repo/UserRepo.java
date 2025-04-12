package com.example.tred_base_test.repo;

import com.example.tred_base_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    // Query method to find user by username
    Optional<User> findByUserName(String userName);
}
