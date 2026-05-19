package com.habitrouter.userservice.model;

import jakarta.persistence.*;
import lombok.Data; //getter, setter, toString(), equals(), hashCode()
import java.time.LocalDateTime;


@Data
@Entity //this class represents a table
@Table ( name = "users" )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generate automatically, serial
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false)
    private LocalDateTime createdAt; //when user registered

    
}
