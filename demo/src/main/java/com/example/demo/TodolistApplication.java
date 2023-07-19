package com.example.demo;

import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class TodolistApplication {
	private final TodoRepository todoRepository;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}