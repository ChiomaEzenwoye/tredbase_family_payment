package com.example.tred_base_test.serviceImpl;

import com.example.tred_base_test.model.User;
import com.example.tred_base_test.repo.ParentRepo;
import com.example.tred_base_test.repo.StudentRepo;
import com.example.tred_base_test.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo, StudentRepo studentRepo, ParentRepo parentRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public Optional<User> findByUsername(String username) {
        return userRepo.findByUserName(username);
    }

    public void deposit(User user, BigDecimal amount) {
        user.setBalance(amount);
        userRepo.save(user);
    }
}
