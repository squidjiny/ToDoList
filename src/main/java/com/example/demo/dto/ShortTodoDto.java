package com.example.demo.dto;
import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ShortTodoDto {

    private String TodoTitle;
    private LocalDateTime startDate;
    private LocalDateTime deadDate;
    private Boolean isImportant;

    @Builder
    public ShortTodoDto(String TODO_title,LocalDateTime startDate ,LocalDateTime deadDate, Boolean isImportant) {
        this.TodoTitle = TODO_title;
        this.startDate = startDate;
        this.deadDate = deadDate;
        this.isImportant = isImportant;
    }
}

