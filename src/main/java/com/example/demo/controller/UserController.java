package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "todo api", description = "todo api")
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
        return ResponseEntity.ok(todos);
    }

    //유저 이름으로 검색기능
    @GetMapping("search")
    public ResponseEntity<List<User>> SearchUsers(@RequestParam("username") String username) {
        List<User> todos = userService.getAllUserinfo();

        List<User> matchingNames = todos.stream()
                .filter(todo -> todo.getUsername().contains(username))
                .collect(Collectors.toList());

        return ResponseEntity.ok(matchingNames);
    }


}

