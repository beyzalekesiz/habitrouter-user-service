package com.habitrouter.userservice.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {


    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private long expiration; 

    //create key: turn jwt code to bytes -> hash -> return key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    //create token using the key
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact(); //turn all into string
    }

    public String extractEmail(String token){
        Key key = getSigningKey();
        return Jwts.parser() //to read tokens
                .verifyWith((SecretKey) key) //check if token is signed: type cast the key to SecretKey
                .build()
                .parseSignedClaims(token) //decrypt and validate token
                .getPayload() //get the data in token
                .getSubject(); //get email
    }
}
