package com.example.demo.dto;
import com.example.demo.domain.User;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDto {

    @NotBlank
    @Schema(example = "frog1234")
    private String username;

    @NotBlank
    @Schema(example = "brook")
    private String password;

    @Email
    @NotBlank
    @ApiModelProperty(example = "example@inu.ac.kr")
    private String userEmail;

    @Schema(example = "[\"ROLE_USER\"]")
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
