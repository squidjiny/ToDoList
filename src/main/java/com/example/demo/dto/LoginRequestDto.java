package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class LoginRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Builder
    public LoginRequestDto(String username,String password){
        this.username = username;
        this.password = password;
    }
}
