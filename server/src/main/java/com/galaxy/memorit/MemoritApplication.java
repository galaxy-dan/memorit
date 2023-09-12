package com.galaxy.memorit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MemoritApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoritApplication.class, args);
	}

}
