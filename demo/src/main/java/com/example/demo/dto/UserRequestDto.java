package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
public class UserRequestDto {

    private Long userid;
    private String username;

    @Builder
    public UserRequestDto(Long userid, String username) {
        this.username = username;
        this.userid = userid;
    }
}
