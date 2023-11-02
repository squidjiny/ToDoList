package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;


@Getter
public class UserRequestDto {
    @Schema(example = "1")
    private Long userid;
    @Schema(example = "frog1234")
    private String username;

    @Builder
    public UserRequestDto(Long userid, String username) {
        this.username = username;
        this.userid = userid;
    }
}
