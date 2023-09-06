package com.example.demo.dto;
import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ShortTodoDto {

    private String TodoTitle;
    private LocalDate startDate;
    private LocalDate deadDate;
    private Boolean isImportant;
    private Boolean isFinished;

    @Builder
    public ShortTodoDto(String TODO_title,LocalDate startDate ,LocalDate deadDate, Boolean isImportant) {
        this.isImportant = isImportant;
        this.deadDate = deadDate;
        this.startDate = startDate;
        this.isFinished = isImportant;
        this.TodoTitle = TODO_title;

    }
}

