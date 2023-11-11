package com.example.demo.domain;

import javax.persistence.*;

import com.example.demo.common.Time;
import com.example.demo.dto.ShortTodoDto;
import com.example.demo.dto.TodoDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "Todo_tb")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Todo extends Time {

    // 할 일 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    //할일 제목
    @Column(nullable = false, name = "todo_title")
    private String todoTitle;

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    //설명
    @Column(nullable = false, name = "dead_date")
    private LocalDate deadDate;

    @Column(nullable = false, name = "todo_description")
    private String todoDescription;

    //중요한 일 여부
    @Column(nullable = false, name = "is_important")
    private Boolean isImportant;

    @Column(nullable=false, name = "is_finished")
    private Boolean isFinished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    public void EditTodo(TodoDto todoDto){
        this.isFinished = todoDto.getIsFinished();
        this.isImportant = todoDto.getIsImportant();
        this.deadDate = todoDto.getDeadDate();
        this.startDate = todoDto.getStartDate();
        this.todoDescription = todoDto.getTodoDescription();
        this.todoTitle = todoDto.getTodoTitle();
    }

    public void TodoToggle(Boolean FT){
        this.isFinished = FT;
    }
}
