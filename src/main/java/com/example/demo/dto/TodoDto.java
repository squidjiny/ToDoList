package com.example.demo.dto;

import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import lombok.Builder;
import lombok.Getter;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

@Getter
public class TodoDto {

    private String todoTitle;
    private String todoDescription;
    private LocalDateTime deadDate;
    private LocalDateTime startDate;
    private Boolean isImportant;

    @Builder
    public TodoDto(String todoTitle,String todoDescription ,LocalDateTime startDate,LocalDateTime deadDate ,Boolean isImportant) {
        this.todoTitle = todoTitle;
        this.todoDescription = todoDescription;
        this.startDate = startDate;
        this.deadDate = deadDate;
        this.isImportant = isImportant;
    }


    public Todo toEntity(User user, TodoDto todoDto) {
        return Todo.builder()
                .user(user)
                .todoTitle(getTodoTitle())
                .todoDescription(getTodoDescription())
                .startDate(getStartDate())
                .deadDate(getDeadDate())
                .isImportant(getIsImportant())
                .build();
    }
}