package com.example.demo.dto;

import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UserResponseDto {

    private Long userId;
    private String username;
    private String userEmail;
    private List<Todo> todoList;

    @Builder
    public UserResponseDto(User user) {
        this.userId = user.getUserid();
        this.username = user.getUsername();
        this.userEmail = user.getUserEmail();
        this.todoList = user.getTodoList();
    }
}
