package com.example.emailverifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EmailVerifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailVerifierApplication.class, args);
	}

}
