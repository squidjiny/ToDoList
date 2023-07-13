package com.mysite.todo.ToDoList;

import com.mysite.todo.ToDoList.todo.TODORepository;
import com.mysite.todo.ToDoList.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class ToDoListApplication {
	private final TODORepository todoRepository;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

}
