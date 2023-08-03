package com.example.demo.dto;
import com.example.demo.domain.User;
import lombok.Builder;
import lombok.Getter;
public class LoginRequestDto {

    private String username;
    private String passwd;

    @Builder
    public LoginRequestDto(String username,String passwd){
        this.username = username;
        this.passwd = passwd;
    }
}
