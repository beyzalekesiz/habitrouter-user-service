package com.habitrouter.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		//add jwt secret as system property
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
