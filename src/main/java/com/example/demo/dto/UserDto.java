package com.example.demo.dto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private Long userid;
    private String username;
    private String userEmail;

    @Builder
    public UserDto(Long userid, String username, String userEmail){
        this.userid = userid;
        this.username = username;
        this.userEmail =userEmail;
    }

}
