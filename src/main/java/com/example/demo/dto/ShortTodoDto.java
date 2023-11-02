package com.example.demo.dto;
import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ShortTodoDto {

    @Schema(example = "4호관에서 밤새 모각코하기")
    private String TodoTitle;
    @Schema(example = "2023-11-12")
    private LocalDate startDate;
    @Schema(example = "2023-11-13")
    private LocalDate deadDate;
    @Schema(example = "true")
    private Boolean isImportant;
    @Schema(example = "false")
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

