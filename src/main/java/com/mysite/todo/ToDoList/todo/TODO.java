package com.mysite.todo.ToDoList.todo;

import com.mysite.todo.ToDoList.DTO.TodoDto;
import com.mysite.todo.ToDoList.common.Time;
import com.mysite.todo.ToDoList.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "TODO_tb")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class TODO extends Time {

        // 할 일 아이디
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long TODO_id;

        //할일 제목
        private String TODO_title;
        //private date Start_date;
        //private date End_date;
        //설명

        @Column(nullable = false)
        private LocalDateTime deadline;

        @Column(nullable = false)
        private String TODO_description;
        //중요한 일 여부
        @Column(nullable = false)
        private boolean important_status;

        //매일하는일 여부
        @Column(nullable = false)
        private boolean daily;

        @Column(nullable=false)
        private boolean finish_TODO;

        @Column(nullable = false)
        private boolean checked;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;

        public void EditTODO(TodoDto todoDto){
                this.TODO_title = todoDto.getTODO_title();
                this.deadline = todoDto.getDeadline();
                this.important_status = todoDto.getImportant_status();
                this.daily = todoDto.getDaily();
        }

        public Boolean GetCharactor(Long todo_id){
                if(important_status = true){
                        return true;
                }
                else{
                        return false;
                }
        }

}
