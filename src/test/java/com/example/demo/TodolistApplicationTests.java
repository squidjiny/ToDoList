package com.example.demo;

import com.example.demo.domain.Todo;
import com.example.demo.dto.ShortTodoDto;
import com.example.demo.repository.TodoRepository;
import com.example.demo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = {TodoService.class, TodoRepository.class})
class TodolistApplicationTests {

	@Autowired
	private TodoService todoService;
	@Test
	public void 투두리스트테스트() {

	}

}
