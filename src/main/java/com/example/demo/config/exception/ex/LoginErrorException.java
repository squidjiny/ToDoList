package com.example.demo.config.exception.ex;

public class LoginErrorException extends RuntimeException{
    public LoginErrorException(){
        super("아이디/비밀번호가 틀렸습니다.");
    }
}
