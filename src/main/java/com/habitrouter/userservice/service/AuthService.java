//service for authentication operations only
package com.habitrouter.userservice.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.habitrouter.userservice.dto.LoginDto;
import com.habitrouter.userservice.dto.RegisterDto;
import com.habitrouter.userservice.model.User;
import com.habitrouter.userservice.repository.UserRepository;
import com.habitrouter.userservice.exception.EmailAlreadyExistsException;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor // generates constructor for final fields (dependency injection)
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User register(RegisterDto registerDto) {

        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("This email is already registered.");
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setName(registerDto.getName());
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public String login (LoginDto loginDto) {
        //check if user exists
        User user = userRepository
                    .findByEmail(loginDto.getEmail())
                    .orElseThrow(()-> new RuntimeException("User not found."));
        //check password
        //matches: take the untouched password, hash it, compare with the given hashed password
        if (!passwordEncoder.matches( loginDto.getPassword(), user.getPassword())){ //normal password 1st, hashed password 2nd
            throw new RuntimeException("Incorrect password.");
        }
        return jwtService.generateToken(user.getEmail());
    }

}
