package com.example.demo.dto;

import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import lombok.Builder;
import lombok.Getter;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class TodoDto {

    private String todoTitle;
    private String todoDescription;
    private LocalDate deadDate;
    private LocalDate startDate;
    private Boolean isImportant;
    private Boolean isFinished;

    @Builder
    public TodoDto(String todoTitle, String todoDescription , LocalDate startDate, LocalDate deadDate , Boolean isImportant, Boolean isFinished) {
        this.todoTitle = todoTitle;
        this.todoDescription = todoDescription;
        this.startDate = startDate;
        this.deadDate = deadDate;
        this.isImportant = isImportant;
        this.isFinished = isFinished;
    }


    public Todo toEntity(User user, TodoDto todoDto) {
        return Todo.builder()
                .user(user)
                .todoDescription(getTodoDescription())
                .isImportant(getIsImportant())
                .isFinished(getIsFinished())
                .deadDate(getDeadDate())
                .startDate(getStartDate())
                .todoTitle(getTodoTitle())
                .build();
    }
}