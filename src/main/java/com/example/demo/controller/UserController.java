package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.domain.User;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    //모든 유저 출력
    @Operation(summary = "모든 유저 출력", description = "서비스에 가입되어있는 모든 유저를 출력함.")
    @GetMapping("")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "유저 출력 성공"),
            @ApiResponse(responseCode = "403", description = "조회 권한이 없습니다.")
    })
    public ResponseEntity<ResponseDto> getAllUsers() {
        List<UserRequestDto> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CommonResponse.SUCCESS, users));
    }

    //유저 이름으로 검색기능
    @Operation(summary = "유저 이름 검색", description = "이름을 파라미터로 받으면 유저의 투두를 출력함")
    @GetMapping("/{username}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "유저 조회 성공"),
            @ApiResponse(responseCode = "404", description = "동일한 유저가 없습니다."),
    })
    public ResponseEntity<ResponseDto> SearchUsers(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CommonResponse.SUCCESS, userService.searchAllUsers(username)));
    }

    //유저 정보 수정
    @Operation(summary = "유저 정보 수정", description = "수정하고 싶은 유저의 아이디와 UserDto 양식에서 수정하고 싶은 내용을 수정해서 파라미터로 받으면 수정사항이 반영됨.")
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> Edit(Long clientId, @RequestBody UserDto userDto){
        userService.editUser(clientId, userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CommonResponse.SUCCESS, userDto));
    }


}

