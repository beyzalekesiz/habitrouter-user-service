package com.habitrouter.userservice.dto;

import lombok.Data;

@Data //for getter & setter
public class LoginDto {
    
    private String email;
    private String password;
}
