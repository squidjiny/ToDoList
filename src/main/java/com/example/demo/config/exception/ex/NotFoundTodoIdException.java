package com.example.demo.config.exception.ex;

public class NotFoundTodoIdException extends RuntimeException{

    public NotFoundTodoIdException(){
        super("해당하는 투두가 없습니다.");
    }
}
