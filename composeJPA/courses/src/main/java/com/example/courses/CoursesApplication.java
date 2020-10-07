package com.example.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
	}

}
