package com.mysite.todo.ToDoList.service;

import com.mysite.todo.ToDoList.user.User;
import com.mysite.todo.ToDoList.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //모든 유저 표시
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
