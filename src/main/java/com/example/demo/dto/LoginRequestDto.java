package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Schema(description = "로그인할때 받는 DTO")
public class LoginRequestDto {

    @NotBlank
    @Schema(description = "사용자가 실제로 사용할 아이디", example = "user123")
    private String username;

    @NotBlank
    @Schema(description = "사용자의 패스워드", example = "user1212")
    private String password;

    @Builder
    public LoginRequestDto(String username,String password){
        this.password = password;
        this.username = username;
    }
}
