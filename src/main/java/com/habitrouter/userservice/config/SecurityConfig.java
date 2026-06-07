package com.habitrouter.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean //objects with one instance that spring manages throughout execution
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // tarayıcı tabanlı uygulamalar için
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() //allow login, request for anyone
                .anyRequest().authenticated() // any other request is locked
            );
        
        return http.build();        

    }
    
}
