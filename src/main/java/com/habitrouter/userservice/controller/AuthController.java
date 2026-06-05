//controller for authentication operations only
package com.habitrouter.userservice.controller;

import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habitrouter.userservice.service.AuthService;
import com.habitrouter.userservice.dto.RegisterDto;
import com.habitrouter.userservice.model.User;

import lombok.RequiredArgsConstructor;

@RestController // @controller + @responsebody
@RequiredArgsConstructor
@RequestMapping ("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto /*turn json into dto*/){ 
        User registeredUser = authService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}