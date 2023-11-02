package com.example.demo.dto;

import com.example.demo.domain.Todo;
import com.example.demo.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class TodoDto {

    @Schema(example = "서버 스터디")
    private String todoTitle;
    @Schema(example = "4호관에서 서버 스터디. 주제: Jpa 간지나게 짜기. 주의: 사용한 어노테이션 의미 파악해서 가기. 아니 나 왜 빼빼로데이에 스터디해 어헝헝")
    private String todoDescription;
    @Schema(example = "2023-11-11")
    private LocalDate deadDate;
    @Schema(example = "2023-11-11")
    private LocalDate startDate;
    @Schema(example = "true")
    private Boolean isImportant;
    @Schema(example = "false")
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