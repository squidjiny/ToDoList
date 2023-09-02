package com.example.demo.dto;
import com.example.demo.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    @ApiModelProperty(example = "example@inu.ac.kr")
    private String userEmail;


    private List<String> role;

    @Builder
    public UserDto(String username,String password, String userEmail, List<String> role){
        this.username = username;
        this.password = password;
        this.userEmail =userEmail;
        this.role = role;
    }

    public User toEntity(UserDto userDto, String passwd){
        return User.builder()
                .username(userDto.getUsername())
                .password(passwd)
                .userEmail(userDto.getUserEmail())
                .roles(userDto.getRole())
                .build();
    }
}
