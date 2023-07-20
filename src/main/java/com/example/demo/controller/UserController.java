package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "유저 정보 api", description = "유저의 회원가입/회원수정/로그인/유저검색 기능을 담당함.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description ="OK!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR!")
    })
    //모든 유저 출력
    @GetMapping("")
    public ResponseEntity<List<UserRequestDto>> getAllUsers() {
        List<UserRequestDto> todos = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(todos);
    }

    //유저 이름으로 검색기능
    @GetMapping("search")
    public ResponseEntity<List<User>> SearchUsers(@RequestParam("username") String username) {
        List<User> todos = userService.getAllUserinfo();

        List<User> matchingNames = todos.stream()
                .filter(todo -> todo.getUsername().contains(username))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(matchingNames);
    }
    //회원가입
    @PostMapping("users/register")
    public ResponseEntity<String> Register(@RequestBody UserDto userDto){
        userService.Register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("회원가입 완료");
    }
    //유저 정보 수정
    @PutMapping("users/edit")
    public ResponseEntity<String> Edit(long userid, @RequestBody UserDto userDto){
        userService.editUser(userid, userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("수정완료");
    }
    //회원탈퇴
    @DeleteMapping("users/withdrawal")
    public ResponseEntity<String> Withdrawal(long userid){
        userService.Withdrawal(userid);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("회원탈퇴 완료");
    }

}

