package com.example.demo.dto;
import com.example.demo.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private String username;
    private String passwd;
    private String userEmail;
    private String role;



    @Builder
    public UserDto(String username,String passwd ,String userEmail){
        this.username = username;
        this.passwd = passwd;
        this.userEmail =userEmail;
    }

    public User toEntity(UserDto Userdto, String passwd){
        return User.builder()
                .username(getUsername())
                .userEmail(getUserEmail())
                .build();
    }

}
