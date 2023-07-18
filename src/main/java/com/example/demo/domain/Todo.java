package com.example.demo.domain;

import javax.persistence.*;

import com.example.demo.common.Time;
import com.example.demo.dto.TodoDto;
import lombok.*;

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
    @Column(nullable = false)
    private String todoTitle;

    @Column(nullable = false)
    private LocalDateTime startDate;

    //설명
    @Column(nullable = false)
    private LocalDateTime deadDate;

    @Column(nullable = false)
    private String todoDescription;

    //중요한 일 여부
    @Column(nullable = false)
    private boolean isImportant;

    @Column(nullable=false)
    private boolean finishTODO;

    @Column(nullable = false)
    private boolean checked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    public void EditTODO(TodoDto todoDto){
        this.todoTitle = todoDto.getTodoTitle();
        this.startDate = todoDto.getStartDate();
        this.deadDate = todoDto.getDeadDate();
        this.isImportant = todoDto.getIsImportant();
    }
}
