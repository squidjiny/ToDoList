package com.mysite.todo.ToDoList.controller;


import com.mysite.todo.ToDoList.service.UserService;
import com.mysite.todo.ToDoList.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    //모든 유저 출력
    @GetMapping("")
        public String getAllUsers(Model model) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "users"; // 템플릿의 이름 반환
        }


    @GetMapping("/{user_id}")
    public String SearchUsersList(@PathVariable String user_id){
        return user_id + "Search result";
    }

    @GetMapping("/friend_list/{user_id}")
    public String FriendsList(@PathVariable String user_id){
        return user_id + "'s Friends";
    }


}
