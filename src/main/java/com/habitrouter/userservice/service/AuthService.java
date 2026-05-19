//service for authentication operations only
package com.habitrouter.userservice.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.habitrouter.userservice.dto.RegisterDto;
import com.habitrouter.userservice.model.User;
import com.habitrouter.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor // generates constructor for final fields (dependency injection)
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterDto registerDto) {

        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new RuntimeException("This email is already registered.");
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setName(registerDto.getName());
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

}
