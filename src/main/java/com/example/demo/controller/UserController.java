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


    @ApiResponses({
            @ApiResponse(responseCode = "200", description ="OK!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR!")
    })
    //모든 유저 출력
    @Operation(summary = "모든 유저 출력", description = "서비스에 가입되어있는 모든 유저를 출력함.")
    @GetMapping("")
    public ResponseEntity<List<UserRequestDto>> getAllUsers() {
        List<UserRequestDto> todos = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(todos);
    }

    //유저 이름으로 검색기능
    @Operation(summary = "유저 이름 검색", description = "이름을 파라미터로 받으면 유저의 투두를 출력함")
    @GetMapping("search/{username}")
    public ResponseEntity<List<User>> SearchUsers(@PathVariable String username) {
        List<User> todos = userService.getAllUserinfo();

        List<User> matchingNames = todos.stream()
                .filter(todo -> todo.getUsername().contains(username))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(matchingNames);
    }
    //회원가입
    @Operation(summary = "회원가입", description = "UserDto 양식에 따라 유저 정보를 입력하고 파라미터로 넣으면 그 정보대로 회원가입이 됨.")
    @PostMapping("users/register")
    public ResponseEntity<String> Register(@RequestBody UserDto userDto){
        userService.Register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("회원가입 완료");
    }
    //유저 정보 수정
    @Operation(summary = "유저 정보 수정", description = "수정하고 싶은 유저의 아이디와 UserDto 양식에서 수정하고 싶은 내용을 수정해서 파라미터로 받으면 수정사항이 반영됨.")
    @PutMapping("users/edit")
    public ResponseEntity<String> Edit(long userid, @RequestBody UserDto userDto){
        userService.editUser(userid, userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("수정완료");
    }
    //회원탈퇴
    @Operation(summary = "회원탈퇴", description = "탈퇴하고싶은 유저의 id를 넣으면 탈퇴가 왼료됨.")
    @DeleteMapping("users/withdrawal")
    public ResponseEntity<String> Withdrawal(long userid){
        userService.Withdrawal(userid);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("회원탈퇴 완료");
    }

}

