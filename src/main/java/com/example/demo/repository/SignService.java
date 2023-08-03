package com.example.demo.repository;

import com.example.demo.dto.SignInResultDto;
import com.example.demo.dto.SignUpResultDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequestDto;

public interface SignService {

    SignUpResultDto signUp(String userid, String password, String userEmail, String role);

    SignInResultDto signIn(String id, String passwd) throws RuntimeException;
}
