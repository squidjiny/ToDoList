package com.example.demo.service;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserResponseDto;


// 예제 13.24
public interface SignService {

    UserResponseDto signUp(UserDto userDto);

    String signIn(LoginRequestDto loginRequestDto) throws RuntimeException;

}