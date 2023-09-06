package com.example.demo.config.exception.ex;

public class NotFoundUserNameException extends RuntimeException {
    public NotFoundUserNameException(){
        super("해당하는 유저가 없습니다.");
    }
}
