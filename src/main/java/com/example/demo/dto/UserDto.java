package com.example.demo.dto;
import com.example.demo.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private String username;
    private String userEmail;

    @Builder
    public UserDto(String username, String userEmail){
        this.username = username;
        this.userEmail =userEmail;
    }

    public User toEntity(UserDto Userdto){
        return User.builder()
                .username(getUsername())
                .userEmail(getUserEmail())
                .build();
    }

}
