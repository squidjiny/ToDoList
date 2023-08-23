package com.example.demo.dto;
import com.example.demo.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    private String userEmail;


    private List<String> role;

    @Builder
    public UserDto(String username,String password, String userEmail, List<String> role){
        this.username = username;
        this.password = password;
        this.userEmail =userEmail;
        this.role = role;
    }

    public User toEntity(UserDto Userdto, String passwd){
        return User.builder()
                .username(getUsername())
                .password(passwd)
                .userEmail(getUserEmail())
                .roles(getRole())
                .build();
    }
}
