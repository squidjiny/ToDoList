package com.example.demo.service;

import com.example.demo.dto.SignInResultDto;
import com.example.demo.dto.SignUpResultDto;


// 예제 13.24
public interface SignService {

    SignUpResultDto signUp(String id, String password, String email, String role);

    SignInResultDto signIn(String id, String password) throws RuntimeException;

}