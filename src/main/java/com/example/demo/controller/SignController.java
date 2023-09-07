package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.domain.User;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.service.SignService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Getter
@RequestMapping("/sign-api")
public class SignController {

    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    /*
    @RequireArgument, @AllArgumentConstructor, @NoArgumentConstructor 를 상황에 따라 쓸 수 있으나
    처음 해보는 단계이기 때문에 눈에 잘 보이는 형식으로 의존성을 주입한다.
    */

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }


    @PostMapping(value = "/sign-in")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "아이디 혹은 비밀번호가 틀렸습니다."),
    })
    public ResponseEntity<ResponseDto> signIn(@Valid @RequestBody LoginRequestDto loginRequestDto)
            throws RuntimeException {
        LOGGER.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", loginRequestDto.getUsername());
        String token = signService.signIn(loginRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CommonResponse.SUCCESS, token));
    }

    @PostMapping(value = "/sign-up")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "동일한 아이디가 존재합니다."),
            @ApiResponse(responseCode = "400", description = "이메일 형식이 틀렸습니다.")
    })
public ResponseEntity<ResponseDto> signUp(@Valid @RequestBody UserDto userDto) {
        LOGGER.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****", userDto.getUsername());
        UserResponseDto userResponseDto = signService.signUp(userDto);
        LOGGER.info("[signUp] 회원가입을 완료했습니다. id : {}", userDto.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CommonResponse.SUCCESS, userResponseDto));
    }

    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }



}