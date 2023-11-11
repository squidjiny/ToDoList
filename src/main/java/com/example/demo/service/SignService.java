package com.example.demo.service;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequestDto;


// 예제 13.24
public interface SignService {

    void signUp(UserDto userDto);

    String signIn(LoginRequestDto loginRequestDto) throws RuntimeException;

    void duplicationCheck(String username);
}