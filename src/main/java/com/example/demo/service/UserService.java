package com.example.demo.service;

import com.example.demo.config.exception.ex.NotFoundTodoIdException;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    //모든 유저 표시
    public List<UserRequestDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserRequestDto> userInfoDtos =  new ArrayList<>();
        for(User user : allUsers ){
           UserRequestDto userInfoDto =  new UserRequestDto(user.getUserid(),user.getUsername());
           userInfoDtos.add(userInfoDto);
        }
        return userInfoDtos;
    }

    public List<UserRequestDto> searchAllUsers(String username){
        List<User> searchAllUsers = userRepository.findAllByUsernameContains(username);

        List<UserRequestDto> searchUsers = searchAllUsers.stream().map(
                user -> new UserRequestDto(
                        user.getUserid(),
                        user.getUsername()
                )).collect(Collectors.toList());
        return searchUsers;
    }


    //유저 수정
    public void editUser(Long userid, UserDto userDto){
        User user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new NotFoundTodoIdException());
        user.EditUser(userDto);
        userRepository.save(user);
    }


}

