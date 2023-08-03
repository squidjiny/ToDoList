package com.example.demo.service;
import com.example.demo.common.CommonResponse;
import com.example.demo.domain.JwtProvider;
import com.example.demo.domain.User;
import com.example.demo.dto.SignUpResultDto;
import com.example.demo.dto.SignInResultDto;
import com.example.demo.repository.SignService;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoginService implements SignService {
    private final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    public UserRepository userRepository;
    public JwtProvider jwtProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public SignUpResultDto signUp(String id, String password, String UserEmail, String role){
        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        User user;
        if(role.equalsIgnoreCase("admin")){
            user = User.builder()
                    .username(id)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        }
        else {
            user= User.builder()
                    .username(id)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();

        }

        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignUpResultDto();

        LOGGER.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if(!savedUser.getUsername().isEmpty()){
            LOGGER.info("[getSignUpResult] 정상 처리 완료");
            setSuccessResult(signUpResultDto);
        }
        else{
            LOGGER.info("[getSignUpResult] 실패 처리 완료");
            setFailResult(signUpResultDto);
        }
        return signUpResultDto;
    }

    @Override
    public SignInResultDto signIn(String id, String passwd) throws RuntimeException{
        LOGGER.info("[getSignInREsult] signDataHandler로 회원 정보 요청");
        User user = userRepository.getByUserid(id);
        LOGGER.info("[getSignInResult] Id : {}", id);

        if(!passwordEncoder.matches(passwd, user.getPassword())){
            throw new RuntimeException();
        }

        LOGGER.info("[getSignInResult] 패스워드 일치");

        SignInResultDto signinResultDto = SignInResultDto.builder()
                .token(jwtProvider.createToken(String.valueOf(user.getUserid()), user.getRoles()))
                .build();

        LOGGER.info("[getSignInResult] 객체에 값 주입");
        setSuccessResult(signinResultDto);

        return signinResultDto;
    }

    private void setSuccessResult(SignUpResultDto result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result){
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
