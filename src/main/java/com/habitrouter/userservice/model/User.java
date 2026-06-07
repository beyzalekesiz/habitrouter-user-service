package com.habitrouter.userservice.model;

import jakarta.persistence.*;
import lombok.Data; //getter, setter, toString(), equals(), hashCode()
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;


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

    @JsonIgnore //don't put this field in response, password musn't return to user
    @Column (nullable = false)
    private String password;

    @Column (nullable = false)
    private LocalDateTime createdAt; //when user registered

    
}
