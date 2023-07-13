package com.mysite.todo.ToDoList.DTO;


import com.mysite.todo.ToDoList.todo.TODO;
import com.mysite.todo.ToDoList.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

    @Getter
    public class TodoDto {

        private String TODO_title;

        private LocalDateTime deadline;
        private Boolean important_status;
        private Boolean daily;

        @Builder
        public TodoDto(String TODO_title, LocalDateTime deadline, Boolean important_status, Boolean daily) {
            this.TODO_title = TODO_title;
            this.deadline = deadline;
            this.important_status = important_status;
            this.daily = daily;
        }
        public TODO toEntity(User user, TodoDto todoDto) {
            return TODO.builder()
                    .user(user)
                    .deadline(todoDto.getDeadline())
                    .TODO_title(todoDto.getTODO_title())
                    .checked(false)
                    .important_status(todoDto.getImportant_status())
                    .daily(todoDto.getDaily())
                    .build();
        }
}
