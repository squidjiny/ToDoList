package com.example.demo.service.impl;

import com.example.demo.config.security.JwtProvider;
import com.example.demo.domain.User;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {

    private final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);

    public UserRepository userRepository;
    public JwtProvider jwtProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(UserRepository userRepository, JwtProvider jwtProvider,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(UserDto userDto) {
        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        User user;
        String password = passwordEncoder.encode(userDto.getPassword());
        user = userDto.toEntity(userDto, password);

        userRepository.save(user);

    }

    @Override
    public String signIn(LoginRequestDto loginRequestDto) throws RuntimeException {
        LOGGER.info("[getSignInResult] signDataHandler 로 회원 정보 요청");
        User user = userRepository.getByUsername(loginRequestDto.getUsername());
        LOGGER.info("[getSignInResult] Id : {}", loginRequestDto.getUsername());

        LOGGER.info("[getSignInResult] 패스워드 비교 수행");
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException();
        }
        LOGGER.info("[getSignInResult] 패스워드 일치");
        String token = jwtProvider.createToken(user.getUsername(), user.getRoles());
        return token;
    }

}