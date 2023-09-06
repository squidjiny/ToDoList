package com.example.demo.config.exception.ex;

public class DuplicatedException extends RuntimeException{
    public DuplicatedException(){
        super("아이디가 중복되었습니다.");
    }
}
