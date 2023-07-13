package com.mysite.todo.ToDoList.DTO;


import lombok.Getter;

@Getter
public class TodoCharacterDTO {

    private boolean important_status;
    private boolean daily;

    public Boolean TodoCharacterDTO(boolean important_status) {
        this.important_status = important_status;
        return important_status;
    }

}
