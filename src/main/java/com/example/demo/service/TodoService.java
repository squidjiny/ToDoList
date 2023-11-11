package com.example.demo.service;

import com.example.demo.config.exception.ex.NotFoundTodoIdException;
import com.example.demo.config.exception.ex.NotFoundUserNameException;
import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import com.example.demo.dto.ShortTodoDto;
import com.example.demo.dto.TodoDto;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;


    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public TodoDto save(Long userid, TodoDto todoDto) {
        User user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new NotFoundUserNameException());
        Todo todo = todoDto.toEntity(user, todoDto);
        todoRepository.save(todo);

        return todoDto;
    }

    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findByTodoId(todoId)
                .orElseThrow(() -> new NotFoundTodoIdException());
        todoRepository.delete(todo);
    }

    public List<ShortTodoDto> getAllTodos(Long userid) {
        List<Todo> todos = todoRepository.findByUserUserid(userid);
        List<ShortTodoDto> todoDTOs = new ArrayList<>();
        for (Todo todo : todos) {
            ShortTodoDto shortTodoDto = new ShortTodoDto(todo.getTodoTitle(), todo.getStartDate(), todo.getDeadDate(), todo.getIsImportant());
            todoDTOs.add(shortTodoDto);
        }
        return todoDTOs;
    }

    public List<ShortTodoDto> getImportantTodos(Long userid) {
        List<Todo> importantTodos = todoRepository.findByUserUseridAndIsImportant(userid, true);
        List<ShortTodoDto> todoDTOs = new ArrayList<>();
        for (Todo todo : importantTodos) {
            ShortTodoDto shortTodoDto = new ShortTodoDto(todo.getTodoTitle(), todo.getStartDate(), todo.getDeadDate(), todo.getIsImportant());
            todoDTOs.add(shortTodoDto);
        }
        return todoDTOs;
    }

    public void edit(Long todoId, TodoDto todoDto){
        Todo todo = todoRepository.findByTodoId(todoId)
                .orElseThrow(() -> new NotFoundTodoIdException());
        todo.EditTodo(todoDto);
        todoRepository.save(todo);
    }
    public List<TodoDto> getUsersAllTodos(Long userid) {
            List<Todo> todos = todoRepository.findByUserUserid(userid);
            List<TodoDto> TodoDtos = new ArrayList<>();
            for (Todo todo : todos) {
                TodoDto TodoDto = new TodoDto(todo.getTodoTitle(), todo.getTodoDescription(), todo.getStartDate(), todo.getDeadDate(), todo.getIsImportant(), todo.getIsFinished());
                TodoDtos.add(TodoDto);
            }
            return TodoDtos;
    }

    public void reverseTodoFinished(Long todoId){
        Optional<Todo> todoOrNull = todoRepository.findByTodoId(todoId);
        if (!todoOrNull.isPresent()) {
            throw new NotFoundTodoIdException();
        }
        Todo todo = todoOrNull.get();
        todo.TodoToggle(todo.getIsFinished() ? false : true);
        todoRepository.save(todo);
    }
    }

