package com.example.tred_base_test.service;

import com.example.tred_base_test.dto.RegisterRequestDto;
import com.example.tred_base_test.model.User;
import com.example.tred_base_test.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    public User registerUser(RegisterRequestDto registerRequestDto) {
        String encodedPassword = passwordEncoder.encode(registerRequestDto.getPassword());

        User newUser = new User();
        newUser.setUsername(registerRequestDto.getUsername());
        newUser.setPassword(encodedPassword);

        return userRepo.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
