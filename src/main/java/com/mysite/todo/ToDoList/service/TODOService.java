package com.mysite.todo.ToDoList.service;

import com.mysite.todo.ToDoList.DTO.TodoDto;
import com.mysite.todo.ToDoList.todo.TODO;
import com.mysite.todo.ToDoList.todo.TODORepository;
import com.mysite.todo.ToDoList.user.User;
import com.mysite.todo.ToDoList.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class TODOService {
    private final TODORepository todoRepository;
    private final UserRepository userRepository;


    //@Transactional
    public void write(Long user_id, TodoDto todoDto) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            TODO todo = todoDto.toEntity(user, todoDto);
            todoRepository.save(todo);
        } else {
            //예외처리
        }
    }

    @Transactional
    public void edit(Long user_id, Long TODO_id, TodoDto todoDto) {
        Optional<TODO> todoOptional = todoRepository.findById(TODO_id);
        if (todoOptional.isPresent()) {
            TODO todo = todoOptional.get();
            todo.EditTODO(todoDto);
        } else {
            //예외처리
        }
    }

    public void deleteTodo(Long TODO_id) {
        Optional<TODO> todoOptional = todoRepository.findById(TODO_id);
        if (todoOptional.isPresent()) {
            todoRepository.deleteById(TODO_id);
        } else {
            //예외처리
        }
    }


    public List<TODO> getTodosByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return todoRepository.findByUser(user);
        }
        else{
            return null;
        }

    }
    //public List<TODO> getImportantTodosByUserId(Long userId) {
    //    return todoRepository.findByUser_user_idAndImportant_status(userId, true);
    //}

}
